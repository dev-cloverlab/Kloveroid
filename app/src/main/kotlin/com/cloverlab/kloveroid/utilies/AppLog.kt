package com.cloverlab.kloveroid.utilies

import android.util.Log

/**
 * Log Module
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
object AppLog {
    private const val COLON: String = ":"
    private const val LEFT_PARENTHESIS: String = "("
    private const val RIGHT_PARENTHESIS: String = ")"
    private const val NULL_STRING: String = ""
    private const val SPACE_STRING: String = " "
    private const val TAG: String = "MY_LOG"  // TAG
    private val IS_DEBUG: Boolean = java.lang.Boolean.TRUE  // Debug mode's switch, default is turn off.
    private val lockLog: Any = Any()  // Avoid the threading's race condition.
    private val strBuilder: StringBuilder = StringBuilder()  // String builder.

    // Log priority level.
    private enum class MsgLevel {
        // Verbose
        v,
        // Debug
        d,
        // Info
        i,
        // Warn
        w,
        // Error
        e
    }

    /**
     * Wrap the checking condition and msg log.
     */
    private class LogWrapper {
        /**
         * Check debug mode and sync.
         *
         * @param cls        class name.
         * @param methodName method name.
         * @param msg        message content.
         * @return success or fail.
         */
        fun debugCheck(cls: Class<*>, methodName: String, msg: Any): Boolean {
            // Checking the debug mode.
            if (IS_DEBUG) {
                // Avoid the race condition.
                synchronized(lockLog) {
                    return this.logMsg(cls, methodName, msg)
                }
            }
            return true
        }

        /**
         * Invoke the Log method to show the log msg.
         *
         * @param cls        class name.
         * @param methodName method name.
         * @param msg        message content.
         * @return success or fail.
         */
        private fun logMsg(cls: Class<*>, methodName: String, msg: Any): Boolean {
            try {
                val method = cls.getDeclaredMethod(methodName, String::class.java, String::class.java)
                method.invoke(null, TAG, msg)
            }
            catch (e: Exception) {
                e.printStackTrace()
                return false
            }

            return true
        }
    }

    /**
     * VERBOSE log.
     *
     * @param msg output message
     */
    @JvmStatic
    fun v(vararg msg: Any?) {
        val msgString = combineInputArguments(*msg)
        LogWrapper().debugCheck(Log::class.java, MsgLevel.v.name, getLogMsg(msgString))
    }

    /**
     * DEBUG log.
     *
     * @param msg output message
     */
    @JvmStatic
    fun d(vararg msg: Any?) {
        val msgString = combineInputArguments(*msg)
        LogWrapper().debugCheck(Log::class.java, MsgLevel.d.name, getLogMsg(msgString))
    }

    /**
     * INFORMATION log.
     *
     * @param msg output message
     */
    @JvmStatic
    fun i(vararg msg: Any?) {
        val msgString = combineInputArguments(*msg)
        LogWrapper().debugCheck(Log::class.java, MsgLevel.i.name, getLogMsg(msgString))
    }

    /**
     * WARNING log.
     *
     * @param msg output message
     */
    @JvmStatic
    fun w(vararg msg: Any?) {
        val msgString = combineInputArguments(*msg)
        LogWrapper().debugCheck(Log::class.java, MsgLevel.w.name, getLogMsg(msgString))
    }

    /**
     * ERROR log.
     *
     * @param msg output message
     */
    @JvmStatic
    fun e(vararg msg: Any?) {
        if (1 == msg.size && msg[0] is Exception)
            LogWrapper().debugCheck(Log::class.java, MsgLevel.e.name, getExceptionMsg(msg[0] as Exception))
        else {
            val msgString = combineInputArguments(*msg)
            LogWrapper().debugCheck(Log::class.java, MsgLevel.e.name, getLogMsg(msgString))
        }
    }

    /**
     * Combine arguments to a string.
     *
     * @param values multiple arguments.
     * @return output string message
     */
    private fun combineInputArguments(vararg values: Any?): String {
        strBuilder.setLength(0)
        values.filter { null != it }.forEach { strBuilder.append(it.toString()).append(SPACE_STRING) }
        return strBuilder.toString()
    }

    /**
     * Combine the meta information and msg.
     *
     * @param msg log message.
     * @return meta information + msg.
     */
    private fun getLogMsg(msg: String?): String = getMetaInfo(null == msg) + COLON + (msg ?: NULL_STRING)

    /**
     * Combine the meta information and exception msg.
     *
     * @param msg exception msg.
     * @return meta information + exception msg.
     */
    private fun getExceptionMsg(msg: Exception): String {
        strBuilder.setLength(0)
        msg.stackTrace.forEach { strBuilder.append(it).append("\n") }

        return strBuilder.toString()
    }


    /**
     * Get the file name, method name, line number.
     *
     * @param isNullString the input string is null no not.
     * @return the format is "methodName(fileName:line)"
     */
    private fun getMetaInfo(isNullString: Boolean): String {
        // Because the function nest so we can get in stack index 3. 
        val tempIndex = 3
        val stackIndex = if (isNullString) tempIndex + 1 else tempIndex
        val ste = Throwable().stackTrace
        strBuilder.setLength(0)

        return strBuilder.append(ste[stackIndex].methodName)
            .append(LEFT_PARENTHESIS)
            .append(ste[stackIndex].fileName)
            .append(COLON)
            .append(ste[stackIndex].lineNumber)
            .append(RIGHT_PARENTHESIS)
            .toString()
    }
}
