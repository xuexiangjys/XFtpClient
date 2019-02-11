# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        ftp-lib

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        src/main/cpp/ftp-lib.cpp
        src/main/cpp/SFML/src/Network/Unix/SocketImpl.cpp
        src/main/cpp/SFML/src/Network/Ftp.cpp
        src/main/cpp/SFML/src/Network/Http.cpp
        src/main/cpp/SFML/src/Network/IpAddress.cpp
        src/main/cpp/SFML/src/Network/Packet.cpp
        src/main/cpp/SFML/src/Network/Socket.cpp
        src/main/cpp/SFML/src/Network/SocketImpl.hpp
        src/main/cpp/SFML/src/Network/SocketSelector.cpp
        src/main/cpp/SFML/src/Network/TcpListener.cpp
        src/main/cpp/SFML/src/Network/TcpSocket.cpp
        src/main/cpp/SFML/src/Network/UdpSocket.cpp
        src/main/cpp/SFML/src/System/Err.cpp
        src/main/cpp/SFML/src/System/String.cpp
        src/main/cpp/SFML/src/System/Time.cpp)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        ftp-lib

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})