# **하위 카테고리 조회** 

상위 카테고리 이용하여 하위의 모든 카테고리 조회

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
        "categoryId": 2,
        "title": "상의",
        "categoryOrder": 3,
        "parentCategoryId": null,
        "subCategories": [
            {
                "categoryId": 8,
                "title": "반팔",
                "categoryOrder": 1,
                "parentCategoryId": 2,
                "subCategories": null
            },
            {
                "categoryId": 7,
                "title": "긴팔",
                "categoryOrder": 2,
                "parentCategoryId": 2,
                "subCategories": null
            }
        ]
    }    
    ```  


