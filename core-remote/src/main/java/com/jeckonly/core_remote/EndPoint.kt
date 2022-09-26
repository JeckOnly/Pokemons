package com.jeckonly.core_remote

/**
 * Calling any API endpoint without a resource ID or name will return a paginated list of available resources for that API.
 * By default, a list "page" will contain up to 20 resources.
 * If you would like to change this just add a 'limit' query parameter to the GET request, e.g. ?limit=60.
 * You can use 'offset' to move to the next page, e.g. ?limit=60&offset=60.
 */
object EndPoint{
    const val POKEMON = "pokemon"
}
