package il.ac.afeka.cloud.reactivegroupsmicroservice.utils

import il.ac.afeka.cloud.reactivegroupsmicroservice.boundaries.GroupBoundary;
import il.ac.afeka.cloud.reactivegroupsmicroservice.entities.GroupEntity
import org.springframework.stereotype.Component;

@Component
class Converter {

    fun toEntity(boundary: GroupBoundary): GroupEntity {
        var entity = GroupEntity()

        entity.id = boundary.id
        entity.name = boundary.name
        entity.creationDate = boundary.creationDate
        entity.description = boundary.description

        return entity
    }

    fun toBoundary(entity: GroupEntity): GroupBoundary {
        var boundary = GroupBoundary()

        boundary.id = entity.id
        boundary.name = entity.name
        boundary.creationDate = entity.creationDate
        boundary.description = entity.description

        return boundary
    }
}



/*
Group SPEC:
{
 "id":"1",
 "name":"DevOps",
 "creationDate":"23-12-2024",
 "description":"Core Group"
}
*/