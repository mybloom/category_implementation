# **카테고리 등록**

카테고리 등록 API

[🏠API목록으로 돌아가기](api.md)


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
    _"title": "상위카고리생성",   
     "parentCategoryId": ""   
  }_
  
  OR
  
  {
      _"title": "하위카고리생성",   
      "parentCategoryId": "1"   
  }_
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