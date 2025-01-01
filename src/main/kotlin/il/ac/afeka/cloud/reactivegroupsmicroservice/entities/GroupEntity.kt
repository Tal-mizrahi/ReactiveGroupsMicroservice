package il.ac.afeka.cloud.reactivegroupsmicroservice.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "GROUPS")
class GroupEntity(
    @Id var id: String?,
    var name: String?,
    var creationDate: String?,
    var description: String?
) {

    constructor(): this(null, null, null, null)


}