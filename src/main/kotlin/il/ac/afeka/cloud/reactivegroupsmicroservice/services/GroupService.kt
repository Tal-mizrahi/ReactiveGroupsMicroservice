package il.ac.afeka.cloud.reactivegroupsmicroservice.services

import il.ac.afeka.cloud.reactivegroupsmicroservice.boundaries.GroupBoundary
import il.ac.afeka.cloud.reactivegroupsmicroservice.boundaries.GroupUserBoundary
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface GroupService {


    fun createGroup(groupBoundary: GroupBoundary) : Mono<GroupBoundary>
    fun getGroupById(groupId: String) : Mono<GroupBoundary>
    fun getAllGroups(page:Int, size: Int): Flux<GroupBoundary>
    fun updateGroup(groupId: String, update: GroupBoundary): Mono<Void>
    fun deleteAllGroups(): Mono<Void>
    // *********************************** BONUS ************************************************
    fun addUserToGroup(groupId: String, email: String): Mono<Void>
    fun getAllUsersOfGroup(groupId: String, page: Int, size: Int): Flux<GroupUserBoundary>
    fun getAllGroupsOfUser(email: String, page: Int, size: Int): Flux<GroupBoundary>
    fun deleteUsersFromGroup(groupId: String): Mono<Void>

}