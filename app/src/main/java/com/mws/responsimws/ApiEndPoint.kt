package com.mws.responsimws

class ApiEndPoint {

    companion object {

        private val SERVER = "http://192.168.56.1/responsi/"
        val CREATE = SERVER+"create.php"
        val READ = SERVER+"read.php"
        val DELETE = SERVER+"delete.php"
        val UPDATE = SERVER+"update.php"
    }
}