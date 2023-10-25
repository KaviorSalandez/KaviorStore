plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.prm392project"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.prm392project"
        minSdk = 34
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.github.smarteist:autoimageslider:1.4.0")
    implementation ("com.github.bumptech.glide:glide:4.14.2")
//    implementation ("com.github.parse-community.Parse-SDK-Android:parse:1.18.5")
//    implementation ("org.slf4j:slf4j-nop:2.0.7")
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    // Utility
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.google.android.gms:play-services-maps:18.1.0")
}