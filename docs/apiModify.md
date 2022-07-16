# **카테고리 수정**

카테고리 수정 API

[🏠API목록으로 돌아가기](api.md)


---

* **URL**

  /category/{categoryId}

* **Method:**

  `PATCH`

* **URL Params**

  None

* **Data Params**

  **Required:**
  ```json  
  {
    "title": "아우터 수정2"   
  }  
  ```  
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