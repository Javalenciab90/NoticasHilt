package com.java90.core.usecases

import com.java90.core.domain.models.Note
import javax.inject.Inject

class GetWordsCount @Inject constructor() {
    operator fun invoke(note: Note) : Int{
        return getCount(note.title) + getCount(note.content)
    }

    private fun getCount(str: String) =
            str.split(" ", "\n")
                    .filter {
                        it.contains(Regex(".*[a-zA-Z].*"))
                    }
                    .count()
}