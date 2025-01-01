package il.ac.afeka.cloud.reactivegroupsmicroservice.services

import il.ac.afeka.cloud.reactivegroupsmicroservice.boundaries.GroupBoundary
import il.ac.afeka.cloud.reactivegroupsmicroservice.boundaries.GroupUserBoundary
import il.ac.afeka.cloud.reactivegroupsmicroservice.crud.GroupCrud
import il.ac.afeka.cloud.reactivegroupsmicroservice.crud.GroupUserRelationCrud
import il.ac.afeka.cloud.reactivegroupsmicroservice.entities.GroupUserRelationEntity
import il.ac.afeka.cloud.reactivegroupsmicroservice.exceptions.BadRequestException
import il.ac.afeka.cloud.reactivegroupsmicroservice.utils.Converter
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class GroupServiceImp(
    val groupCrud: GroupCrud,
    val groupUserRelationCrud: GroupUserRelationCrud,
    val converter:Converter) : GroupService {
    override fun createGroup(groupBoundary: GroupBoundary): Mono<GroupBoundary> {
        return  Mono.just(groupBoundary)
            .flatMap{
                if(it.name.isNullOrBlank()) {
                    Mono.error {BadRequestException("Name must not be null") } // Mono<Mono<error>>
                }
                else if (it.description.isNullOrBlank()) {
                    Mono.error {BadRequestException("Description must not be null")} // Mono<Mono<error>>
                }else {
                    it.id = null
                    it.creationDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                    Mono.just(it)
                } // Mono<Mono<GroupBoundary>>
            } // Mono<GroupBoundary / error>
            .map{this.converter.toEntity(it)} // Mono<GroupEntity>
            .log()
            .flatMap { groupCrud.save(it) } // Mono<GroupEntity>
            .map { this.converter.toBoundary(it) } // Mono<GroupBoundary>
            .log()
}


    override fun getGroupById(groupId: String): Mono<GroupBoundary> {
        return this.groupCrud
            .findById(groupId)
            .map { this.converter.toBoundary(it)}
            .log()
    }

    override fun getAllGroups(page: Int, size: Int): Flux<GroupBoundary> {
        return this.groupCrud
            .findAllByIdNotNull(PageRequest.of(page,size, Sort.Direction.ASC, "creationDate", "id"))
            .log()
            .map { this.converter.toBoundary(it) }
            .log()
    }

    override fun updateGroup(groupId: String, update: GroupBoundary): Mono<Void> {
        return this.groupCrud
            .findById(groupId)
            .flatMap {
                if(!update.name.isNullOrBlank()){ // Update group name if it is not null or blank
                    it.name = update.name
                }
                if(!update.description.isNullOrBlank()){ // Update group description if it is not null or blank
                    it.description = update.description
                }
                this.groupCrud.save(it)
            }
            .map { this.converter.toBoundary(it) }
            .log()
            .then()
    }

    override fun deleteAllGroups(): Mono<Void> {

        return this.groupCrud
            .deleteAll()
            .log()
            .zipWith(
                this.groupUserRelationCrud
                    .deleteAll()
                    .log()
            )
            .then()

    }
    // *********************************** BONUS ************************************************

    override fun addUserToGroup(groupId: String, email: String): Mono<Void> {
        return this.groupUserRelationCrud.findByGroupIdAndUserEmail(groupId, email)
            .switchIfEmpty (
                // If no relation exists, proceed to add the user to the group
                this.groupCrud.findById(groupId)
                    .map { GroupUserRelationEntity(null, it.id, email) }
                    .flatMap { this.groupUserRelationCrud.save(it) }

            )
            .then()
    }

    override fun getAllUsersOfGroup(groupId: String, page: Int, size: Int): Flux<GroupUserBoundary> {
        return this.groupUserRelationCrud
            .findAllByGroupId(groupId, PageRequest.of(page, size, Sort.Direction.ASC, "userEmail"))
            .log()
            .map{ GroupUserBoundary(it.userEmail) }
            .log()
    }

    override fun getAllGroupsOfUser(email: String, page: Int, size: Int): Flux<GroupBoundary> {
        return this.groupUserRelationCrud
            .findAllByUserEmail(email, PageRequest.of(page, size, Sort.Direction.ASC, "groupId"))
            .log()
            .flatMapSequential { this.groupCrud.findById(it.groupId!!) }
            .map {this.converter.toBoundary(it) }
            .log()
    }

    override fun deleteUsersFromGroup(groupId: String): Mono<Void> {
        return this.groupUserRelationCrud
            .deleteAllByGroupId(groupId)
            .log()
    }

}