package il.ac.afeka.cloud.reactivegroupsmicroservice.boundaries

class GroupUserBoundary(
    var email: String?) {

    constructor() : this(null)

    override fun toString(): String {
        return "{\n\t" +
                "email='$email\n'" +
                "}"
    }

}