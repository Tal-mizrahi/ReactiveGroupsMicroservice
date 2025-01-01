package il.ac.afeka.cloud.reactivegroupsmicroservice.crud

import il.ac.afeka.cloud.reactivegroupsmicroservice.entities.GroupEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface GroupCrud : ReactiveMongoRepository<GroupEntity, String> {

    fun findAllByIdNotNull(pageable: Pageable): Flux<GroupEntity>



}