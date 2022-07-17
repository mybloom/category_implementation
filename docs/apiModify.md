# **카테고리 수정**

카테고리 수정 API

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
  
  or
  
  {
    "categoryId": 16,
    "title": "상위카테고리 이름 수정2",  
    "subCategories": [
            {
                "categoryId": 17,
                "title": "하위카고리 수정1",
                "categoryOrder": 1             
            },
            {
                "categoryId": 18,
                "title": "하위카고리 수정2",
                "categoryOrder": 2
            }
        ]
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