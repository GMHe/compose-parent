@echo off & setlocal enabledelayedexpansion
set BIN_DIR=%~dp0

::cnf
set APP_MAIN=cn.ecpark.infrastructure.yangjian.custom.Application

::run
cd %BIN_DIR%\..
  set APP_CP=""
  for %%f in (lib\*.jar) do set APP_CP=!APP_CP!;%%f
  ::echo libs: %APP_CP%

  java -Xms512m -Xmx5g -cp ".\runtime;%APP_CP%" %APP_MAIN%
cd %BIN_DIR%

