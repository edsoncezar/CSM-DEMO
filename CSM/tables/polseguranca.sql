/******************************************************************************/
/****               Generated by IBExpert 29/7/2007 1:44:51                ****/
/******************************************************************************/

SET SQL DIALECT 3;

SET NAMES NONE;



/******************************************************************************/
/****                                Tables                                ****/
/******************************************************************************/



CREATE TABLE POLSEGURANCA (
    POLID                       UID NOT NULL /* UID = VARCHAR(38) NOT NULL */,
    CONTEXTO                    ID NOT NULL /* ID = VARCHAR(38) */,
    DESCRICAO                   DESCRICAO NOT NULL /* DESCRICAO = VARCHAR(60) NOT NULL */,
    NIVEL_SEGURANCA             VARCHAR(32),
    LIMITE_LOGINS_ERRADOS       VARCHAR(32),
    DIAS_TROCA_SENHA            VARCHAR(32),
    PERMITE_LOGIN_SIMULTANEO    VARCHAR(32),
    TAMANHO_MINIMO_SENHA        VARCHAR(32),
    PODE_ALTERAR_SENHA          VARCHAR(32),
    ULTIMAS_SENHAS_PROIBIDAS    VARCHAR(32),
    CONTEUDO_SENHA              VARCHAR(32),
    OBRIGA_MAIUSCULO_MINUSCULO  VARCHAR(32),
    CHECKSUM                    VARCHAR(64)
);




/******************************************************************************/
/****                             Primary Keys                             ****/
/******************************************************************************/

ALTER TABLE POLSEGURANCA ADD CONSTRAINT PK_POLSEGURANCA PRIMARY KEY (POLID);


/******************************************************************************/
/****                             Descriptions                             ****/
/******************************************************************************/

DESCRIBE TABLE POLSEGURANCA
'pol';



/* Fields descriptions */

DESCRIBE FIELD NIVEL_SEGURANCA TABLE POLSEGURANCA
'Baixo - N�o verifica sequencia de senhas
Medio - Verifica padr�o de senhas �bvias
Alto - Verifica padr�o de senhas �bvias e dicion�rio de senhas proibidas';



/******************************************************************************/
/****                              Privileges                              ****/
/******************************************************************************/


/* Privileges of users */
GRANT ALL ON POLSEGURANCA TO FABIO;
GRANT ALL ON POLSEGURANCA TO FERNANDO;
GRANT ALL ON POLSEGURANCA TO USRRHSTUDIO WITH GRANT OPTION;

/* Privileges of roles */
GRANT ALL ON POLSEGURANCA TO RHSTUDIO WITH GRANT OPTION;