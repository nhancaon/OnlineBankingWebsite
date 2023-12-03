<div class="fixed top-28 w-72 right-2 my-2 animate-notification z-[10001]">
    <c:if test="${not empty requestScope.successMessage}">
        <div
            class="flex items-center p-4 mb-4 text-sm text-green-800 rounded-lg bg-green-100"
            role="alert"
            >
            <svg
                class="flex-shrink-0 inline w-4 h-4 me-3"
                aria-hidden="true"
                xmlns="http://www.w3.org/2000/svg"
                fill="currentColor"
                viewBox="0 0 20 20"
                >
                <path
                    d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z"
                    />
            </svg>
            <span class="sr-only">Info</span>
            <div>
                <span class="font-medium">${requestScope.successMessage}</span>
            </div>
        </div>
    </c:if>

    <c:if test="${not empty requestScope.errorMessage}">
        <div
            class="flex items-center p-4 mb-4 text-sm text-red-800 rounded-lg bg-red-100"
            role="alert"
            >
            <svg
                class="flex-shrink-0 inline w-4 h-4 me-3"
                aria-hidden="true"
                xmlns="http://www.w3.org/2000/svg"
                fill="currentColor"
                viewBox="0 0 20 20"
                >
                <path
                    d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z"
                    />
            </svg>
            <span class="sr-only">Info</span>
            <div>
                <span class="font-medium">${requestScope.errorMessage}</span>
            </div>
        </div>
    </c:if>
</div>            <div class="fixed top-28 w-72 right-2 my-2 animate-notification">
    <c:if test="${not empty requestScope.successMessage}">
        <div
            class="flex items-center p-4 mb-4 text-sm text-green-800 rounded-lg bg-green-100"
            role="alert"
            >
            <svg
                class="flex-shrink-0 inline w-4 h-4 me-3"
                aria-hidden="true"
                xmlns="http://www.w3.org/2000/svg"
                fill="currentColor"
                viewBox="0 0 20 20"
                >
                <path
                    d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z"
                    />
            </svg>
            <span class="sr-only">Info</span>
            <div>
                <span class="font-medium">${requestScope.successMessage}</span>
            </div>
        </div>
    </c:if>

    <c:if test="${not empty requestScope.errorMessage}">
        <div
            class="flex items-center p-4 mb-4 text-sm text-red-800 rounded-lg bg-red-100"
            role="alert"
            >
            <svg
                class="flex-shrink-0 inline w-4 h-4 me-3"
                aria-hidden="true"
                xmlns="http://www.w3.org/2000/svg"
                fill="currentColor"
                viewBox="0 0 20 20"
                >
                <path
                    d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z"
                    />
            </svg>
            <span class="sr-only">Info</span>
            <div>
                <span class="font-medium">${requestScope.errorMessage}</span>
            </div>
        </div>
    </c:if>
</div>