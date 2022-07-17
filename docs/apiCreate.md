# **카테고리 등록**

카테고리 등록 API

---

* **URL**

  /category

* **Method:**

  `POST`

* **URL Params**

  None

* **Data Params**

  **Required:**
  ```json  
  {
    "title": "상위카고리 등록",   
     "parentCategoryId": ""   
  }
  
  OR
  
  {
    "title": "하위카고리 등록",   
    "parentCategoryId": "1"   
  }
  ```  

* **Error Response:**
    * **Code:** 404 NOT FOUND <br />
      **Content:** `{ "message": "해당 categoryId는 존재하지 않은 카테고리입니다." }`
    * **Code:** 400 NOT FOUND <br />
      **Content:** `{ "message": "유효한 카테고리 depth가 아닙니다. 유효한 카테고리 depth는 2입니다." }`

* **Success Response:**
    * **Code:** 200 <br />
    * **Response Sample :**

      ```json
      {
        "categoryId": 14
      }
      ```
