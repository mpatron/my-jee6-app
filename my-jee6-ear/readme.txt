<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<artifactId>my-jee6-ear</artifactId>
	<packaging>ear</packaging>
	<name>Java EE ear</name>

	<parent>
		<groupId>org.jobjects</groupId>
		<artifactId>my-jee6-app</artifactId>
		<version>1.0</version>
	</parent>

	<dependencies>
		<dependency>
			<groupId>org.jobjects</groupId>
			<artifactId>my-jee6-war</artifactId>
			<version>1.0</version>
			<type>war</type>
		</dependency>

		<dependency>
			<groupId>org.jobjects</groupId>
			<artifactId>my-jee6-ejb</artifactId>
			<version>1.0</version>
			<type>ejb</type>
		</dependency>
	</dependencies>
	<!-- <properties> <netbeans.hint.deploy.server>gfv3ee6</netbeans.hint.deploy.server> 
		</properties> -->
	<build>
		<plugins>

			<plugin>
				<groupId>org.glassfish</groupId>
				<artifactId>maven-embedded-glassfish-plugin</artifactId>
				<version>3.1.1</version>
				<configuration>
					<serverID>server</serverID>
					<name>${project.build.finalName}</name>
					<app>${project.build.directory}/${project.build.finalName}.ear</app>
					<!-- 
					<app>${project.build.directory}/${project.build.finalName}-${project.version}.ear</app>
					 -->
					<installRoot>${env.AS_HOME}</installRoot>
					<instanceRoot>${env.AS_HOME}/glassfish/domains/domain1</instanceRoot>
					<autoDelete>true</autoDelete>
					<!-- <instanceRoot>${project.build.directory}/gfe-${maven.build.timestamp}</instanceRoot> -->
					<ports>
						<!-- <http-listener>8080</http-listener> <https-listener>8181</https-listener> -->
					</ports>
					<configFile>${env.AS_HOME}/glassfish/domains/domain1/config/domain.xml</configFile>
					<deploymentParams>
					<!-- 
						<param>${project.build.directory}/${project.build.finalName}-${project.version}.ear</param>
						<param>- -contextroot=my-jee6</param>
						<param>- -createtables=true</param>
						<param>- -force=true</param>
						<param>- -precompilejsp=true</param>
					 -->
					</deploymentParams>
				</configuration>
				<executions>
					<execution>
						<phase>install</phase>
						<goals>
							<goal>start</goal> <!-- this will run during pre-integration-test phase -->
							<goal>deploy</goal> <!-- this will run during pre-integration-test phase -->
							<goal>undeploy</goal> <!-- this will run during post-integration-test phase -->
							<goal>stop</goal> <!-- this will run during post-integration-test phase -->
							<!-- <goal>run</goal> -->
						</goals>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>

</project>