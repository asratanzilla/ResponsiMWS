package com.mws.responsimws

class ApiEndPoint {

    companion object {

        private val SERVER = "http://127.0.0.1/anows/responsi/"
        val CREATE = SERVER+"create.php"
        val READ = SERVER+"read.php"
        val DELETE = SERVER+"delete.php"
        val UPDATE = SERVER+"update.php"
    }

}