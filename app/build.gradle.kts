import java.util.Properties


val properties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    properties.load(localPropertiesFile.inputStream())
}
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "hopeapps.dedev.sptrans"
    compileSdk = 35

    defaultConfig {
        applicationId = "hopeapps.dedev.sptrans"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "API_KEY", "\"${properties.getProperty("API_KEY")}\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_KEY", "\"${properties.getProperty("API_KEY")}\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.core)
    implementation(libs.bundles.compose)
    debugImplementation(libs.bundles.debugTools)
    testImplementation(libs.bundles.unitTests)
    androidTestImplementation(libs.bundles.androidTests)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.retrofitBundle)
    implementation(libs.bundles.koin)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.google.maps.android.compose)
    implementation(libs.google.maps.android.utils.ktx)
    implementation(libs.google.maps.android.utils.ktx)
    implementation(libs.play.services.maps)
    implementation(libs.google.maps.compose.utils)

}