package com.example.reliancehmo.models

class AddProviderResponse: Response() {

    /**
        {
            "status": "success",
            "data": [
                {
                    "id": 1,
                },
            ]
        }
     */


    /**
    {
        "status": "success",
        "data": {
            "id": 1,
        }
    }
     */

    var message: String? = null

    // lateinit var data: List<Id>

    lateinit var data: Id

}