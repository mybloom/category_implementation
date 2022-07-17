# **카테고리 삭제**

카테고리 삭제 API

[🏠API목록으로 돌아가기](api.md)


---

* **URL**

  /category/{categoryId}

* **Method:**

  `DELETE`

* **URL Params**

  None

* **Data Params**

  **Required:**
  None

* **Error Response:**
    * **Code:** 404 NOT FOUND <br />
      **Content:** `{ "message": "해당 categoryId는 존재하지 않은 카테고리입니다." }`

* **Success Response:**
    * **Code:** 200 <br />
    * **Response Sample :**

      ```json
      {
        "categoryId": 14
      }
      ```