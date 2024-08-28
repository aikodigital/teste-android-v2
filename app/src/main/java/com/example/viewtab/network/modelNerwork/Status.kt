package com.example.viewtab.network.modelNerwork

enum class Status(val value: Int) {

    LOADING(0) {
        override fun toString(): String {
            return "LOADING"
        }
    },
    SUCCESS(1) {
        override fun toString(): String {
            return "OK"
        }
    },
    ERROR(2) {
        override fun toString(): String {
            return "ERROR"
        }
    };

    companion object {
        fun valueOf(value: Int): Status {
            return values().find { it.value == value } ?: throw IllegalArgumentException("The Status enum has no entry that match for the value informed.")
        }

        fun parse(value: String): Status {
            return values().find { it.toString().equals(value, ignoreCase = true) } ?: throw IllegalArgumentException("The Status enum has no entry that match for the value informed.")
        }
    }

}