package eu.hexgate.alternativeworld.domain.common

import io.vavr.collection.Map

interface InMemoryRepositoryView<T> {

    val repoView: Map<Long, T>

}