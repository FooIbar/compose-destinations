plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.devtools.ksp") version Versions.ksp
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/debug/kotlin")
    }
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.ramcosta.samples.destinationstodosample"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    packagingOptions {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

dependencies {

    implementation(project(mapOf("path" to ":compose-destinations")))
    ksp(project(":compose-destinations-ksp"))

    with(Deps.Android) {
        implementation(material)
    }

    with(Deps.Compose) {
        implementation(ui)
        implementation(material)
        implementation(viewModel)
        implementation(navigation)
    }

    with(Deps.AndroidX) {
        implementation(lifecycleRuntimeKtx)
        implementation(activityCompose)
    }
}