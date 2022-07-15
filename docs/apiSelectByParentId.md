# **카테고리 조회** 

상위 카테고리 이용하여 하위의 모든 카테고리 조회

[🏠API목록으로 돌아가기](api.md)


---

* **URL**

  /category/{categoryId}

* **Method:**

  `GET`

* **URL Params**

  **Required:**

  `categoryId=[integer]`

* **Data Params**

  None

* **Success Response:**

    * **Code:** 200 <br />
      **Content:** `{ id : 12, name : "Michael Bloom" }`

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
      **Content:** `{ error : "User doesn't exist" }`

* **Response Sample :**

  ```json
    {
        "categoryId": 1,
        "title": "아우터",
        "parentCategoryId": null,
        "subCategories": [
            {
                "categoryId": 4,
                "title": "쟈켓",
                "parentCategoryId": 1
            },
            {
                "categoryId": 5,
                "title": "가디건",
                "parentCategoryId": 1
                },
            {
                "categoryId": 6,
                "title": "블루종",
                "parentCategoryId": 1
            }
        ]
    }

  ```