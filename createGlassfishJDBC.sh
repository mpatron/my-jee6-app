export GLASSFISH_HOME=/home/mickael/programs/glassfish
$GLASSFISH_HOME/bin/asadmin stop-domain
cp /home/mickael/.m2/repository/postgresql/postgresql/9.0-801.jdbc4/postgresql-9.0-801.jdbc4.jar $GLASSFISH_HOME/glassfish/domains/domain1/lib/
$GLASSFISH_HOME/bin/asadmin start-domain
$GLASSFISH_HOME/bin/asadmin create-jdbc-connection-pool --restype javax.sql.XADataSource --datasourceclassname org.postgresql.xa.PGXADataSource --steadypoolsize 2 --property User=javauser:DatabaseName=mabase:Password=javapassword:ServerName=localhost:PortNumber=5432 maBasePool
$GLASSFISH_HOME/bin/asadmin ping-connection-pool maBasePool
$GLASSFISH_HOME/bin/asadmin create-jdbc-resource --connectionpoolid=maBasePool jdbc/maBase

