package il.ac.afeka.cloud.reactivegroupsmicroservice.controllers

import il.ac.afeka.cloud.reactivegroupsmicroservice.boundaries.GroupBoundary;
import il.ac.afeka.cloud.reactivegroupsmicroservice.boundaries.GroupUserBoundary
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import il.ac.afeka.cloud.reactivegroupsmicroservice.services.GroupService;
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = ["/groups"])
class GroupController(
    val groupService: GroupService
) {

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun createGroup(
        @RequestBody groupBoundary: GroupBoundary
    ): Mono<GroupBoundary> {
        return this.groupService
            .createGroup(groupBoundary)
    }
//
    @GetMapping(
        path = ["/{groupId}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getGroupById(
        @PathVariable("groupId") groupId: String
    ): Mono<GroupBoundary> {
        return this.groupService
            .getGroupById(groupId)
    }

    @GetMapping(
        produces = [MediaType.TEXT_EVENT_STREAM_VALUE]
    )
    fun getAllGroups(
        @RequestParam(name = "page", required = false, defaultValue = "0") page: Int,
        @RequestParam(name = "size", required = false, defaultValue = "10") size: Int): Flux<GroupBoundary> {
        return this.groupService.getAllGroups(page, size)
    }

    @PutMapping(
        path = ["/{groupId}"],
        consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateGroup(
        @PathVariable("groupId") groupId: String,
        @RequestBody update: GroupBoundary
    ): Mono<Void> {
        return this.groupService
            .updateGroup(groupId, update)
    }

    @DeleteMapping
    fun deleteAllGroups (): Mono<Void> {
        return this.groupService
            .deleteAllGroups();
    }

    // *********************************** BONUS ************************************************

    @PutMapping(
        path = ["/{groupId}/users"],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun addUserToGroup(
        @PathVariable("groupId") groupId: String,
        @RequestBody user: GroupUserBoundary
    ): Mono<Void>{
        return this.groupService
            .addUserToGroup(groupId, user.email!!)
    }


    @GetMapping(
        path = ["/{groupId}/users"],
        produces = [MediaType.TEXT_EVENT_STREAM_VALUE]
    )
    fun getAllUsersOfGroup(
        @PathVariable("groupId") groupId: String,
        @RequestParam(name = "page", required = false, defaultValue = "0") page: Int,
        @RequestParam(name = "size", required = false, defaultValue = "10") size: Int
    ): Flux<GroupUserBoundary> {
        return this.groupService
            .getAllUsersOfGroup(groupId, page, size)

    }


    @GetMapping(
        path = ["/{email}/groups"],
        produces = [MediaType.TEXT_EVENT_STREAM_VALUE]
    )
    fun getAllGroupsOfUser(
        @PathVariable("email") email: String,
        @RequestParam(name = "page", required = false, defaultValue = "0") page: Int,
        @RequestParam(name = "size", required = false, defaultValue = "10") size: Int
    ): Flux<GroupBoundary> {
        return this.groupService
            .getAllGroupsOfUser(email, page, size)

    }

    @DeleteMapping(
        path = ["/{groupId}/users"])
    fun deleteUserFromGroup(
        @PathVariable("groupId") groupId: String):Mono<Void>{
        return this.groupService
            .deleteUsersFromGroup(groupId)
    }
}

