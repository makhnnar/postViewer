package com.pedrogomez.postviewer.util

import androidx.lifecycle.liveData
import com.pedrogomez.postviewer.models.db.UserTable

class DataHelper {

    companion object{
        val HITTABLE = UserTable(
            "objectId",
            "author",
            1000,
            "story_title",
            "story_url",
            "title",
            "url"
        )
        val HITSRESPONSE = HitsListResponse(
            listOf(
                HitResponse(
                    "objectId",
                    "author",
                    1000,
                    "story_title",
                    "story_url",
                    "title",
                    "url"
                ),
                HitResponse(
                    "objectId1",
                    "author1",
                    10001,
                    "story_title1",
                    "story_url1",
                    "title1",
                    "url1"
                )
            )
        )
        val HITSLIST = listOf(
            HITTABLE
        )
        val LIVEHISTDATA = liveData<List<UserTable>> { HITSLIST }
        val EMPTYHISTS : List<UserTable> = emptyList()
    }

}