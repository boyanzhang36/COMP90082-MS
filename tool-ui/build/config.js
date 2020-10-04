{
  "name": "Edit Tables",
  "favicon": "https://www.commoninja.com/public/favicon.ico",
  "baseUrl": "http://alb-4ae742000766bd01.elb.us-east-2.amazonaws.com:8081/api/v1",
  "pages": [

    {
      "name": "Doctor",
      "id": "doctors",
      "description": "Manage doctor data",
      "methods": {
        "getAll": {
          "label": "Get All",
          
          "url": "/doctors",
          "queryParams": [
            {
              "name": "search",
              "value": "",
              "label": "Search",
              "type": "text"
            }
          ],
          "display": {
            "type": "table"
          },
          "fields": [

            {
              "name": "name",
              "type": "text",
              "label": "Name"
            },
            {
              "name": "bio",
              "type": "text",
              "label": "BIO"
            },
            {
              "name": "address",
              "type": "text",
              "label": "ADDRESS"
            },
            {
              "name": "phone",
              "type": "text",
              "label": "PHONE"
            },
            {
              "name": "fax",
              "type": "text",
              "label": "FAX"
            },
            {
              "name": "email",
              "type": "text",
              "label": "EMAIL"
            },
            {
              "name": "website",
              "type": "text",
              "label": "WEBSITE"
            },
            {
              "name": "expertise",
              "type": "text",
              "label": "EXPERTISE"
            }
            
          ]
        },
        
        "put": {
          "url": "/doctors/:id",
          "fields": [

            {
              "name": "name",
              "type": "text",
              "label": "Name"
            },
            {
              "name": "bio",
              "type": "text",
              "label": "BIO"
            },
            {
              "name": "address",
              "type": "text",
              "label": "ADDRESS"
            },
            {
              "name": "phone",
              "type": "text",
              "label": "PHONE"
            },
            {
              "name": "fax",
              "type": "text",
              "label": "FAX"
            },
            {
              "name": "email",
              "type": "text",
              "label": "EMAIL"
            },
            {
              "name": "website",
              "type": "text",
              "label": "WEBSITE"
            },
            {
              "name": "expertise",
              "type": "text",
              "label": "EXPERTISE"
            }
          ]
        },
        "post": {
          "url": "/doctors",
          "fields": [

            {
              "name": "name",
              "type": "text",
              "label": "Name"
            },
            {
              "name": "bio",
              "type": "text",
              "label": "BIO"
            },
            {
              "name": "address",
              "type": "text",
              "label": "ADDRESS"
            },
            {
              "name": "phone",
              "type": "text",
              "label": "PHONE"
            },
            {
              "name": "fax",
              "type": "text",
              "label": "FAX"
            },
            {
              "name": "email",
              "type": "text",
              "label": "EMAIL"
            },
            {
              "name": "website",
              "type": "text",
              "label": "WEBSITE"
            },
            {
              "name": "expertise",
              "type": "text",
              "label": "EXPERTISE"
            }
          ]
        },
        "delete": {
          "url": "/doctors/:id"
        }
      }
    },
    
    {
      "name": "Hospital",
      "id": "hospitals",
      "description": "Manage hospital data",
      "methods": {
        "getAll": {
          "label": "Get All",
          "url": "/hospitals",
          "queryParams": [
            {
              "name": "search",
              "value": "",
              "label": "Search",
              "type": "text"
            }
          ],
          "display": {
            "type": "table"
          },
          "fields": [
            {
              "name": "name",
              "type": "text",
              "label": "NAME"
            },
            {
              "name": "address",
              "type": "text",
              "label": "ADDRESS"
            },
            {
              "name": "phone",
              "type": "text",
              "label": "PHONE"
            },
            {
              "name": "aftPhone",
              "type": "text",
              "label": "aftPhone"
            },
            {
              "name": "email",
              "type": "text",
              "label": "EMAIL"
            },
            {
              "name": "fax",
              "type": "text",
              "label": "FAX"
            },
            {
              "name": "website",
              "type": "text",
              "label": "WEBSITE"
            },
            {
              "name": "emergencyDept",
              "type": "text",
              "label": "emergencyDept"
            }
            
          ]
        },

        "put": {
          "url": "/hospitals/:id",
          "fields": [
            {
              "name": "name",
              "type": "text",
              "label": "NAME"
            },
            {
              "name": "address",
              "type": "text",
              "label": "ADDRESS"
            },
            {
              "name": "phone",
              "type": "text",
              "label": "PHONE"
            },
            {
              "name": "aftPhone",
              "type": "text",
              "label": "aftPhone"
            },
            {
              "name": "email",
              "type": "text",
              "label": "EMAIL"
            },
            {
              "name": "fax",
              "type": "text",
              "label": "FAX"
            },
            {
              "name": "website",
              "type": "text",
              "label": "WEBSITE"
            },
            {
              "name": "emergencyDept",
              "type": "text",
              "label": "emergencyDept"
            }
          ]
        },
        "post": {
          "url": "/hospitals",
          "fields": [
            {
              "name": "name",
              "type": "text",
              "label": "NAME"
            },
            {
              "name": "address",
              "type": "text",
              "label": "ADDRESS"
            },
            {
              "name": "phone",
              "type": "text",
              "label": "PHONE"
            },
            {
              "name": "aftPhone",
              "type": "text",
              "label": "aftPhone"
            },
            {
              "name": "email",
              "type": "text",
              "label": "EMAIL"
            },
            {
              "name": "fax",
              "type": "text",
              "label": "FAX"
            },
            {
              "name": "website",
              "type": "text",
              "label": "WEBSITE"
            },
            {
              "name": "emergencyDept",
              "type": "text",
              "label": "emergencyDept"
            }
          ]
        },
        "delete": {
          "url": "/hospitals/:id"
        }
      }
    },
    {
      "name": "User",
      "id": "users",
      "description": "Manage user data",
      "methods": {
        "getAll": {
          "label": "Get All",
          "url": "/users",
          "queryParams": [
            {
              "name": "search",
              "value": "",
              "label": "Search",
              "type": "text"
            }
          ],
          "display": {
            "type": "table"
          },
          "fields": [
            {
              "name": "firstname",
              "type": "text",
              "label": "FIRSTNAME"
            },
            {
              "name": "middlename",
              "type": "text",
              "label": "MIDDLENAME"
            },
            {
              "name": "surname",
              "type": "text",
              "label": "LASTNAME"
            },
            {
              "name": "password",
              "type": "text",
              "label": "PASSWORD"
            },
            {
              "name": "dob",
              "type": "text",
              "label": "DOB"
            },
            {
              "name": "token",
              "type": "text",
              "label": "TOKEN"
            },
            {
              "name": "email",
              "type": "text",
              "label": "EMAIL"
            },
            {
              "name": "street",
              "type": "text",
              "label": "STREET"
            },
            {
              "name": "suburb",
              "type": "text",
              "label": "SUBURB"
            },
            {
              "name": "state",
              "type": "text",
              "label": "STATE"
            },
            {
              "name": "tokenvalidfrom",
              "type": "text",
              "label": "TOKENVALIDFROM"
            },
            {
              "name": "tokenexpiredate",
              "type": "text",
              "label": "TOKENEXPIRYDATE"
            },
            {
              "name": "role",
              "type": "text",
              "label": "ROLE"
            }
            
          ]
        },

        "put": {
          "url": "/users/:id",
          "fields": [
            {
              "name": "firstname",
              "type": "text",
              "label": "FIRSTNAME"
            },
            {
              "name": "middlename",
              "type": "text",
              "label": "MIDDLENAME"
            },
            {
              "name": "surname",
              "type": "text",
              "label": "LASTNAME"
            },
            {
              "name": "password",
              "type": "text",
              "label": "PASSWORD"
            },
            {
              "name": "dob",
              "type": "text",
              "label": "DOB"
            },
            {
              "name": "token",
              "type": "text",
              "label": "TOKEN"
            },
            {
              "name": "email",
              "type": "text",
              "label": "EMAIL"
            },
            {
              "name": "street",
              "type": "text",
              "label": "STREET"
            },
            {
              "name": "suburb",
              "type": "text",
              "label": "SUBURB"
            },
            {
              "name": "state",
              "type": "text",
              "label": "STATE"
            },
            {
              "name": "tokenvalidfrom",
              "type": "text",
              "label": "TOKENVALIDFROM"
            },
            {
              "name": "tokenexpiredate",
              "type": "text",
              "label": "TOKENEXPIRYDATE"
            },
            {
              "name": "role",
              "type": "text",
              "label": "ROLE"
            }
          ]
        },
        "post": {
          "url": "/users",
          "fields": [
            {
              "name": "firstname",
              "type": "text",
              "label": "FIRSTNAME"
            },
            {
              "name": "middlename",
              "type": "text",
              "label": "MIDDLENAME"
            },
            {
              "name": "surname",
              "type": "text",
              "label": "LASTNAME"
            },
            {
              "name": "password",
              "type": "text",
              "label": "PASSWORD"
            },
            {
              "name": "dob",
              "type": "text",
              "label": "DOB"
            },
            {
              "name": "token",
              "type": "text",
              "label": "TOKEN"
            },
            {
              "name": "email",
              "type": "text",
              "label": "EMAIL"
            },
            {
              "name": "street",
              "type": "text",
              "label": "STREET"
            },
            {
              "name": "suburb",
              "type": "text",
              "label": "SUBURB"
            },
            {
              "name": "state",
              "type": "text",
              "label": "STATE"
            },
            {
              "name": "tokenvalidfrom",
              "type": "text",
              "label": "TOKENVALIDFROM"
            },
            {
              "name": "tokenexpiredate",
              "type": "text",
              "label": "TOKENEXPIRYDATE"
            },
            {
              "name": "role",
              "type": "text",
              "label": "ROLE"
            }
          ]
        },
        "delete": {
          "url": "/users/:id"
        }
      }
    },
        {
      "name": "Resources",
      "id": "resources",
      "description": "Manage resource data",
      "methods": {
        "getAll": {
          "label": "Get All",
          "url": "/resources",
          "queryParams": [
            {
              "name": "search",
              "value": "",
              "label": "Search",
              "type": "text"
            }
          ],
          "display": {
            "type": "table"
          },
          "fields": [
            {
              "name": "uid",
              "type": "text",
              "label": "UID"
            },
            {
              "name": "name",
              "type": "text",
              "label": "NAME"
            },
            {
              "name": "website",
              "type": "text",
              "label": "WEBSITE"
            }
            
          ]
        },
        "getSingle": {
          "url": "/resources/:id",
          "queryParams": [],
          "requestHeaders": {}
        },
        "put": {
          "url": "/resources/:id",
          "fields": [
            {
              "name": "uid",
              "type": "text",
              "label": "UID"
            },
            {
              "name": "name",
              "type": "text",
              "label": "NAME"
            },
            {
              "name": "website",
              "type": "text",
              "label": "WEBSITE"
            }
          ]
        },
        "post": {
          "url": "/resources",
          "fields": [
            {
              "name": "uid",
              "type": "text",
              "label": "UID"
            },
            {
              "name": "name",
              "type": "text",
              "label": "NAME"
            },
            {
              "name": "website",
              "type": "text",
              "label": "WEBSITE"
            }
          ]
        },
        "delete": {
          "url": "/resources/:id"
        }
      }
    }
  ]
}