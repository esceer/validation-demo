# validation-demo

Proof of concept project for trying out spring validation capabilities.

# Test scenarios:

### Valid dto

#### Request:

```
{
    "id": "id",
    "mode": "enabled",
    "code": "fitting",
    "embeddedM": {
        "name": "name"
    }
}
```

#### Response:

`200 OK`

---

### Standard javax validators fail

#### Request:

```
{
    "mode": "enabled",
    "code": "definitely_not_fitting",
    "embeddedM": {
        "name": "name"
    }
}
```

#### Response:

```
{
    "timestamp": "2022-07-13T14:14:42.397+00:00",
    "status": 400,
    "error": "Bad Request",
    "message": "Validation failed for object='dto'. Error count: 2",
    "errors": [
        {
            "codes": [
                "NotNull.dto.id",
                "NotNull.id",
                "NotNull.java.lang.String",
                "NotNull"
            ],
            "arguments": [
                {
                    "codes": [
                        "dto.id",
                        "id"
                    ],
                    "arguments": null,
                    "defaultMessage": "id",
                    "code": "id"
                }
            ],
            "defaultMessage": "Please fill in 'id' field",
            "objectName": "dto",
            "field": "id",
            "rejectedValue": null,
            "bindingFailure": false,
            "code": "NotNull"
        },
        {
            "codes": [
                "Size.dto.code",
                "Size.code",
                "Size.java.lang.String",
                "Size"
            ],
            "arguments": [
                {
                    "codes": [
                        "dto.code",
                        "code"
                    ],
                    "arguments": null,
                    "defaultMessage": "code",
                    "code": "code"
                },
                10,
                0
            ],
            "defaultMessage": "size must be between 0 and 10",
            "objectName": "dto",
            "field": "code",
            "rejectedValue": "definitely_not_fitting",
            "bindingFailure": false,
            "code": "Size"
        }
    ],
    "path": "/"
}
```

---

### Embedded dto is invalid

#### Request:

```
{
    "id": "id",
    "mode": "enabled",
    "code": "fitting",
    "embeddedM": {
    }
}
```

#### Response:

```
{
    "timestamp": "2022-07-13T14:16:44.518+00:00",
    "status": 400,
    "error": "Bad Request",
    "message": "Validation failed for object='dto'. Error count: 1",
    "errors": [
        {
            "codes": [
                "NotNull.dto.embeddedM.name",
                "NotNull.embeddedM.name",
                "NotNull.name",
                "NotNull.java.lang.String",
                "NotNull"
            ],
            "arguments": [
                {
                    "codes": [
                        "dto.embeddedM.name",
                        "embeddedM.name"
                    ],
                    "arguments": null,
                    "defaultMessage": "embeddedM.name",
                    "code": "embeddedM.name"
                }
            ],
            "defaultMessage": "must not be null",
            "objectName": "dto",
            "field": "embeddedM.name",
            "rejectedValue": null,
            "bindingFailure": false,
            "code": "NotNull"
        }
    ],
    "path": "/"
}
```

---

### All the validators report errors - including standard, embedded and custom validators

#### Request:

```
{
    "mode": "not_enabled",
    "code": "definitely_not_fitting",
    "embeddedM": {
    }
}
```

#### Response:

```
{
    "timestamp": "2022-07-13T14:20:02.438+00:00",
    "status": 400,
    "error": "Bad Request",
    "message": "Validation failed for object='dto'. Error count: 4",
    "errors": [
        {
            "codes": [
                "DtoLevelConstraint.dto",
                "DtoLevelConstraint"
            ],
            "arguments": [
                {
                    "codes": [
                        "dto.",
                        ""
                    ],
                    "arguments": null,
                    "defaultMessage": "",
                    "code": ""
                }
            ],
            "defaultMessage": "Invalid dto",
            "objectName": "dto",
            "code": "DtoLevelConstraint"
        },
        {
            "codes": [
                "Size.dto.code",
                "Size.code",
                "Size.java.lang.String",
                "Size"
            ],
            "arguments": [
                {
                    "codes": [
                        "dto.code",
                        "code"
                    ],
                    "arguments": null,
                    "defaultMessage": "code",
                    "code": "code"
                },
                10,
                0
            ],
            "defaultMessage": "size must be between 0 and 10",
            "objectName": "dto",
            "field": "code",
            "rejectedValue": "definitely_not_fitting",
            "bindingFailure": false,
            "code": "Size"
        },
        {
            "codes": [
                "NotNull.dto.id",
                "NotNull.id",
                "NotNull.java.lang.String",
                "NotNull"
            ],
            "arguments": [
                {
                    "codes": [
                        "dto.id",
                        "id"
                    ],
                    "arguments": null,
                    "defaultMessage": "id",
                    "code": "id"
                }
            ],
            "defaultMessage": "Please fill in 'id' field",
            "objectName": "dto",
            "field": "id",
            "rejectedValue": null,
            "bindingFailure": false,
            "code": "NotNull"
        },
        {
            "codes": [
                "NotNull.dto.embeddedM.name",
                "NotNull.embeddedM.name",
                "NotNull.name",
                "NotNull.java.lang.String",
                "NotNull"
            ],
            "arguments": [
                {
                    "codes": [
                        "dto.embeddedM.name",
                        "embeddedM.name"
                    ],
                    "arguments": null,
                    "defaultMessage": "embeddedM.name",
                    "code": "embeddedM.name"
                }
            ],
            "defaultMessage": "must not be null",
            "objectName": "dto",
            "field": "embeddedM.name",
            "rejectedValue": null,
            "bindingFailure": false,
            "code": "NotNull"
        }
    ],
    "path": "/"
}
```