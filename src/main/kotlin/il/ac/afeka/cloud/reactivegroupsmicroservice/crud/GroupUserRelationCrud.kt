package il.ac.afeka.cloud.reactivegroupsmicroservice.crud

import il.ac.afeka.cloud.reactivegroupsmicroservice.entities.GroupEntity
import il.ac.afeka.cloud.reactivegroupsmicroservice.entities.GroupUserRelationEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.repository.query.Param
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface GroupUserRelationCrud : ReactiveMongoRepository<GroupUserRelationEntity, String> {

    fun findByGroupIdAndUserEmail( @Param("groupId") groupId:String,@Param("userEmail") userEmail:String): Mono<GroupUserRelationEntity>
    fun findAllByGroupId( @Param("groupId") groupId:String, pageable: Pageable): Flux<GroupUserRelationEntity>
    fun findAllByUserEmail( @Param("userEmail") userEmail:String, pageable: Pageable): Flux<GroupUserRelationEntity>
    fun deleteAllByGroupId( @Param("groupId") groupId:String): Mono<Void>
}