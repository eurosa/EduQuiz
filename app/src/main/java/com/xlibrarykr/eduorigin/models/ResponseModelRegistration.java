    package com.xlibrarykr.eduorigin.models;

    public class ResponseModelRegistration {

        String message;

        public ResponseModelRegistration(String message) {
            this.message = message;
        }

        public ResponseModelRegistration() {
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
