# **전체 카테고리 조회**

상위 카테고리 미지정시 모든 카테고리 조회

[🏠API목록으로 돌아가기](api.md)


---

* **URL**

  /category

* **Method:**

  `GET`


* **URL Params**

  **Required:**

  None

* **Data Params**

  None

* **Error Response:**

  None

* **Success Response:**

  * **Code:** 200 <br />
  * **Response Sample :**

  ```json
    [
        {
            "categoryId": 1,
            "title": "아우터",
            "parentCategoryId": null,
            "subCategories": [
                {
                    "categoryId": 4,
                    "title": "쟈켓",
                    "parentCategoryId": 1,
                    "subCategories": null
                },
                {
                    "categoryId": 5,
                    "title": "가디건",
                    "parentCategoryId": 1,
                    "subCategories": null
                },
                {
                    "categoryId": 6,
                    "title": "블루종",
                    "parentCategoryId": 1,
                    "subCategories": null
                }
            ]
        },
        {
            "categoryId": 2,
            "title": "상의",
            "parentCategoryId": null,
            "subCategories": [
                {
                    "categoryId": 7,
                    "title": "긴팔",
                    "parentCategoryId": 2,
                    "subCategories": null
                },
                {
                    "categoryId": 8,
                    "title": "반팔",
                    "parentCategoryId": 2,
                    "subCategories": null
                }
            ]
        },
        {
            "categoryId": 3,
            "title": "바지",
            "parentCategoryId": null,
            "subCategories": [
                {
                    "categoryId": 9,
                    "title": "긴바지",
                    "parentCategoryId": 3,
                    "subCategories": null
                },
                {
                    "categoryId": 10,
                    "title": "반바지",
                    "parentCategoryId": 3,
                    "subCategories": null
                }
            ]
        }
    ]

  ```