plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "app.bruner.rodrigobrunertask5"
    compileSdk = 34

    defaultConfig {
        applicationId = "app.bruner.rodrigobrunertask5"
        minSdk = 28
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
    implementation(libs.roomruntime)
    implementation(libs.room.common)
    annotationProcessor(libs.roomcompiler)
    implementation(libs.lifecycleextensions)
    annotationProcessor(libs.lifecyclecompile)

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}