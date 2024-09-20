extra["GROUP_ID"] = "com.example"
extra["ARTIFACT_ID"] = "kotlin-template"
extra["GH_USERNAME"] = System.getenv("GITHUB_ACTOR") ?: ""
extra["GH_TOKEN"] = System.getenv("GITHUB_TOKEN") ?: ""
extra["REPOSITORY_NAME"] = System.getenv("GITHUB_REPOSITORY") ?: ""

val repositoryName = extra["REPOSITORY_NAME"].toString()
val (organization, repo) = repositoryName.split("/", limit = 2).let { it[0] to it.getOrElse(1) { "" } }

extra["ORGANIZATION"] = organization
extra["REPO"] = "${repo}"
