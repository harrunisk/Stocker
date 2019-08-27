/*
 * App Modules
 */
object Modules {

    private const val BASE_DIRECTORY = ":base"
    private const val DASBOARD_DIRECTORY = ":dashboard"

    /*
     * App
     */
    const val app = ":app"

    /*
     * Core Modules
     */
    const val corePresentation = "$BASE_DIRECTORY:core_presentation"
    const val coreDomain = "$BASE_DIRECTORY:core_domain"
    const val coreData = "$BASE_DIRECTORY:core_data"
    const val core = "$BASE_DIRECTORY:core"
    const val navigation = "$BASE_DIRECTORY:navigation"

    /*
    * Core Modules
    */
    const val dashboardPresentation = "$DASBOARD_DIRECTORY:dashboard_presentation"
    const val dashboardDomain = "$DASBOARD_DIRECTORY:dashboard_domain"
    const val dashboardData = "$DASBOARD_DIRECTORY:dashboard_data"
}