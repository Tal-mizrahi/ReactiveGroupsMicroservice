package il.ac.afeka.cloud.reactivegroupsmicroservice.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "GROUP_USER_RELATION")
class GroupUserRelationEntity(
    @Id var id: String?,
    var groupId: String?,
    var userEmail: String?

) {
    constructor() : this(null, null, null)

}