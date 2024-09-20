import java.net.HttpURLConnection
import java.net.URL

val repositoryOwner = extra["ORGANIZATION"].toString()
val packageName = "${extra["GROUP_ID"]}.${extra["ARTIFACT_ID"]}".toString()
val version = extra["SCM_VERSION"].toString()
val githubToken = extra["GH_TOKEN"].toString()


fun determineOwnerType(owner: String, token: String): String {
    val userUrl = URL("https://api.github.com/users/$owner")
    val connection = userUrl.openConnection() as HttpURLConnection
    connection.requestMethod = "GET"
    connection.setRequestProperty("Authorization", "Bearer $token")

    return if (connection.responseCode == 200) {
        "users"
    } else {
        "orgs"
    }
}

tasks.register("checkVersion") {
    doLast {
        if (githubToken.isEmpty()) {
            throw GradleException("GITHUB_TOKEN environment variable is not set")
        }

        val ownerType = determineOwnerType(repositoryOwner, githubToken)

        val url = URL("https://api.github.com/${ownerType}/$repositoryOwner/packages/maven/$packageName/versions")

        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("Authorization", "Bearer $githubToken")

        when (connection.responseCode) {
            200 -> {
                val response = connection.inputStream.bufferedReader().readText()
                if (response.contains("\"name\":\"$version\"")) {
                    println("Version $version already exists, skipping publication.")
                    throw GradleException("Version $version already exists, aborting build.")
                } else {
                    println("Version $version not found, proceeding with publication...")
                }
            }
            404 -> throw GradleException("Unable to locate package or repo: $repositoryOwner/$packageName (HTTP 404)")
            else -> throw GradleException("Failed to get the list of versions: HTTP ${connection.responseCode}")
        }
    }
}
