package il.ac.afeka.cloud.reactivegroupsmicroservice.boundaries

class GroupBoundary(
    var id: String?,
    var name: String?,
    var creationDate: String?,
    var description: String?) {

    constructor() : this(null, null, null, null)

    override fun toString(): String {
        return "{\n\t" +
                "id: $id,\n\t" +
                "name: $name,\n\t" +
                "creationDate: $creationDate,\n\t" +
                "description: $description\n" +
                "}"
    }


}


