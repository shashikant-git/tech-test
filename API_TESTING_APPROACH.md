# API Testing Approach

This document outlines the high-level strategy for testing the PATCH `/objects/{id}` endpoint of the [restful-api.dev](https://restful-api.dev/) API, as implemented in the `RestfulApiPatchTest.java` class.

## High-Level Strategy

### Functional Testing
- **Objective**: Ensure the PATCH endpoint behaves correctly with valid inputs and handles errors appropriately.
- **Approach**:
    - Validate PATCH updates with valid data (e.g., partial updates to fields like `name`).
    - Test error handling for common failure cases (e.g., invalid ID, malformed JSON).

### Negative Testing
- **Objective**: Verify the endpoint’s robustness against invalid or unexpected inputs.
- **Approach**:
    - Send requests without authentication (if applicable, though not required for this public API).
    - Use invalid content types (e.g., `text/plain` instead of `application/json`) or malformed payloads.

### Performance Testing
- **Objective**: Assess the endpoint’s efficiency under load.
- **Approach**:
    - Measure response time for PATCH requests under load (planned for future scope, not implemented in this demo).

### Exploratory Testing Scenarios
- **Objective**: Identify edge cases and potential vulnerabilities through creative testing.
- **Scenarios**:
    - Test PATCH with edge-case data:
        - Empty strings (e.g., `""` for `name`).
        - Null values (e.g., `null` in the payload).
        - Oversized payloads (e.g., excessively long strings).
    - Attempt concurrent PATCH requests to the same ID to check for race conditions or locking behavior.
    - Verify behavior with unsupported HTTP methods (e.g., sending a PUT request instead of PATCH).

## Tools Used
- **RestAssured**: For sending HTTP requests and validating responses.
- **TestNG**: For structuring and running the automated tests.

## Notes
- The current implementation includes functional and negative tests (valid PATCH and invalid ID scenarios).
- Performance and exploratory testing are outlined as future enhancements to demonstrate a comprehensive approach.

For implementation details, see `src/test/java/org/practice/RestfulApiPatchTest.java`.