# Cấu trúc dự án theo Layered Architecture

#### Mô hình Layered Architecture, chia package theo tầng nghiệp vụ.

**1. `controller`:**

*   **Nhiệm vụ:** Xử lý các request từ client và gọi các service tương ứng để thực hiện nghiệp vụ.
*   **Vai trò:** Lớp tiếp xúc trực tiếp với client và chịu trách nhiệm trả về response.

**2. `service`:**

*   **Nhiệm vụ:** Chứa logic nghiệp vụ của ứng dụng.
*   **Vai trò:** Nhận request từ controller, thực hiện các thao tác cần thiết và trả về kết quả cho controller.

**3. `repository`:**

*   **Nhiệm vụ:** Tương tác trực tiếp với cơ sở dữ liệu.
*   **Vai trò:** Cung cấp các phương thức để truy vấn, thêm, sửa, xóa dữ liệu từ cơ sở dữ liệu. 

**4. `model`:**

*   **Nhiệm vụ:** Chứa các class đại diện cho dữ liệu của ứng dụng, phục vụ cho việc trao đổi và lưu trữ dữ liệu.
    *   **`entity` :** Chứa các class đại diện cho các bảng trong cơ sở dữ liệu.
    *   **`dto` :** Chứa các class dùng để truyền dữ liệu giữa các layer của ứng dụng.
    *   **`enums` :** Chứa các enum định nghĩa các giá trị cố định, giúp code dễ đọc và bảo trì hơn.
    *   **`vo` :** Chứa các class đại diện cho các giá trị cụ thể, thường là immutable và so sánh bằng giá trị thay vì bằng identity.

**5. `config`:**

*   **Nhiệm vụ:** Chứa các file cấu hình cho ứng dụng.
*   **Vai trò:** Thiết lập các thông số hoạt động cho ứng dụng, ví dụ: cấu hình database, cấu hình security, cấu hình CORS.

**6. `security`:**

*   **Nhiệm vụ:** Chứa các class liên quan đến bảo mật của ứng dụng.
*   **Vai trò:** Xử lý các tác vụ xác thực (authentication) và phân quyền (authorization).

**7. `exception`:**

*   **Nhiệm vụ:** Chứa các class exception để xử lý các lỗi xảy ra trong ứng dụng.
*   **Vai trò:** Định nghĩa các loại lỗi có thể xảy ra và cung cấp cơ chế để xử lý chúng một cách thống nhất.

**8. `util`:**

*   **Nhiệm vụ:** Chứa các class tiện ích (utility classes) được sử dụng trong toàn ứng dụng.
*   **Vai trò:** Cung cấp các hàm hỗ trợ (helper functions) cho các tác vụ phổ biến, giúp code ngắn gọn và dễ tái sử dụng.

**9. `constant`:**

*   **Nhiệm vụ:** Chứa các hằng số (constants) được sử dụng trong toàn ứng dụng.
*   **Vai trò:** Tập trung các giá trị không thay đổi tại một nơi, giúp dễ dàng quản lý và thay đổi khi cần thiết, tránh "magic numbers" và strings trong code. Ví dụ:
    *   Các hằng số liên quan đến database: tên bảng, tên cột.
    *   Các hằng số liên quan đến JWT: secret key, thời gian hết hạn.
    *   Các hằng số liên quan đến API: URL endpoints, content types.
    *   Các thông báo lỗi/thành công mặc định.
    *  . . . .