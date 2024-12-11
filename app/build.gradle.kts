plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.pausi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.pausi"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // Dependencias de soporte y diseño
    implementation(libs.appcompat) // AppCompat para compatibilidad entre versiones
    implementation(libs.material) // Material Design para NavigationView y componentes
    implementation(libs.activity) // Actividad principal y componentes básicos
    implementation(libs.constraintlayout) // Layouts con ConstraintLayout

    // Dependencias adicionales necesarias
    implementation("com.google.android.material:material:1.9.0")

    implementation("androidx.drawerlayout:drawerlayout:1.1.1") // DrawerLayout
    implementation("androidx.fragment:fragment-ktx:1.6.1") // Manejo simplificado de fragmentos
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.3") // Navegación con fragmentos
    implementation("androidx.navigation:navigation-ui-ktx:2.7.3") // Manejo de UI con navegación

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
