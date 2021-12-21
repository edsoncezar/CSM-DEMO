CREATE TABLE CSMUSER(
    USERID                     INTEGER NOT NULL,       /* PRIMARY KEY*/
    CONTEXTID                  INTEGER NOT NULL,       /* FOREIGN KEY CONTEXT*/
    USERLOGIN                  VARCHAR(15) NOT NULL ,   /* LOGIN ID, UNIQUE*/
    NAME                       VARCHAR(60) NOT NULL,   /*FULL NAME*/
    PROFILEID                  INTEGER NOT NULL, /* FOREIGN KEY PROFILE*/
    ROLE                       VARCHAR(50),
    OBSERVATIONS               TEXT,
    LASTLOGIN_DATETIME         DATETIME,
    FL_ACTIVE                  BOOLEAN,
    CREATEDDATETIME            VARCHAR (25),
    CREATEDBYUSERID            INTEGER /* ID DO USUÁRIO QUE CRIOU O USUÁRIO*/
);

CREATE TABLE CONTACT (
    USERID                     INTEGER NOT NULL,       /* PRIMARY KEY*/
    AREA                       VARCHAR(50),
    PHONE                      VARCHAR(50),
    MOBILE                     VARCHAR(50),
    EMAIL                      VARCHAR(70),

)

CREATE TABLE CSMUSERPASSWORD (
    USERID                     INTEGER NOT NULL,       /* PRIMARY KEY*/
    PASSWORD                   CHAR(32) NOT NULL,     /* hash md5*/
    EXPIRATION_DATE            DATE,
    LASTCHANGE_DATE            DATE,
    PASSQUESTION               VARCHAR (50), /* QUESTION FOR RETRIEVING PASSWORD*/
    PASSANSWER                 VARCHAR (50), /* ANSWER FOR RETRIEVING PASSWORD*/
    FL_CHANGE_PASS_NEXT_LOGIN  BOOLEAN
)
