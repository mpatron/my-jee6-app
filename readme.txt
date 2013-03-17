asadmin list-domains

asadmin delete-domain mydomain

asadmin create-domain --savemasterpassword=true --savelogin=true mydomain
nom administrateur : god
mot de passe administrateur : soleil
masterpassword : lune

asadmin list-jdbc-connection-pools

asadmin create-jdbc-connection-pool --datasourceclassname org.postgresql.xa.PGXADataSource --restype javax.sql.XADataSource --property User=javauser:Password=javapassword:DatabaseName=mabase:ServerName=localhost --initsql="select version();" --ping=true PostgresPool

asadmin create-jdbc-resource --connectionpoolid PostgresPool jdbc/__PostgresPool

asadmin create-jdbc-resource --connectionpoolid PostgresPool jdbc/maBase

asadmin ping-connection-pool PostgresPool

redeploy --name my-jee6-ear-1.0 --properties keepSessions=true C:/Users/Mickael/Documents/java/github/my-jee6-app/my-jee6-ear/target/my-jee6-ear-1.0.ear

deploy --force=true --keepstate=false C:/Users/Mickael/Documents/java/github/my-jee6-app/my-jee6-ear/target/my-jee6-ear-1.0.ear