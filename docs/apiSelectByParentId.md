# **하위 카테고리 조회** 

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

* **Error Response:**
    * **Code:** 400 Bad Request <br />
      **Content:** `{  "message": "상위카테고리만 조회가능합니다." }`
  
    * **Code:** 404 NOT FOUND <br />
      **Content:** `{ "message": "해당 categoryId는 존재하지 않은 카테고리입니다." }`

* **Success Response:**
    * **Code:** 200 <br />
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