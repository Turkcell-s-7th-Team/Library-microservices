📚 Library Microservices Projesi
Bu proje, kütüphane yönetim sistemini modern bir microservices (mikroservisler) mimarisiyle hayata geçiren, Domain-Driven Design (DDD) ve CQRS desenlerini uygulayan dağıtık bir uygulamadır. Her bir servis, bağımsız olarak geliştirilebilir, dağıtılabilir ve ölçeklendirilebilir yapıda tasarlanmıştır.

🧩 Proje Hakkında
Bu proje, Domain-Driven Design (DDD), CQRS, Event-Driven Architecture ve Mikroservis mimarisi ilkeleri kullanılarak geliştirilmiş bir kütüphane yönetim sistemidir.
Her mikroservis kendi bounded context’i içerisinde çalışır ve kendi veritabanına sahiptir.

DDD ve CQRS Uygulaması
DDD: Her bir microservice (örneğin Member-Service veya Book-Service), iş mantığı ve verilerini içeren sınırlandırılmış bir bağlam (Bounced Context) etrafında tasarlanmıştır.
CQRS: Servisler, komutların (veri değişikliği) ve sorguların (veri okuma) ayrıştırıldığı bir yapıdadır. Basit CRUD işlemleri her servisin kendi veritabanında yapılır.

🏗️ Mimarinin Temel Yapısı
🧠 Domain-Driven Design (DDD)

Her servis kendi domain’ine sahiptir:
⚙️ Servislerin Sorumlulukları
| Servis                  | Açıklama                                                   |
| ----------------------- | ---------------------------------------------------------- |
| **member-service**      | Üyelik, üye bilgisi, status yönetimi (ACTIVE/BANNED)       |
| **book-service**        | Kitap yönetimi, stok kontrolü, kategori ve yayıncı bilgisi |
| **loan-service**        | Loan oluşturma, iade etme, member ve book validasyonları   |
| **fine-service**        | Gecikme cezaları hesaplama ve oluşturma                    |
| **reservation-service** | Kitap rezervasyon işlemleri                                |
| **author-service**      | Yazar bilgileri yönetimi                                   |
| **publisher-service**   | Yayıncı bilgileri                                          |
| **category-service**    | Kitap kategorileri                                         |
| **staff-service**       | Personel işlemleri                                         |
| **config-server**       | Merkezi konfigürasyon                                      |
| **discovery-server**    | Service discovery (Eureka)                                 |
| **gateway-server**      | API Gateway (routing + security)                           |

Her servis kendi:
Aggregate Root
Entity & Value Object
Domain Service
Domain Event
Repository
yapılarını içerir.

İletişim Mekanizmaları
A. Senkron İletişim (OpenFeign ile Validasyon)
Kritik ön-işlem validasyonları için servisler arası anlık iletişim gereklidir. Bu, OpenFeign kullanılarak sağlanır:

📌Ödünç Alma (Loan) Süreci Örneği:
Loan Service, bir ödünç alma komutu aldığında:
✔ OpenFeign kullanarak Member Service'e gidip üyenin BANNED (Yasaklı) olup olmadığını kontrol eder.
✔ Aynı şekilde Book Service'e giderek kitabın available copies (mevcut kopya sayısı) kontrolünü yapar.
✔ Validasyonlar başarılı olursa, ödünç alma işlemi devam eder.

📌Loan Service, bir kitabı geri getirme komutu aldığında:
✔loan-service bir LoanReturnedEvent yayınlar.
✔  book-service availableCopies += 1 yapar.

B. Asenkron İletişim (Apache Kafka ile Olay Tabanlı Güncellemeler)
Veri tutarlılığını sağlamak ve servisleri birbirinden bağımsız kılmak için olay tabanlı mimari (Event-Driven Architecture) kullanılır. Kafka bu olayların taşınması için kullanılır:

Örnek 1: Üyelik Güncelleme (Banned Status):
Fine Service, bir üyeye ceza (fine) oluşturduğunda bir Kafka olayı (FineCreatedEvent) yayınlar.
Member Service, bu olayı tüketir, ilgili üyenin veritabanındaki status bilgisini BANNED olarak günceller.

Örnek 2: Kitap Stok Güncelleme:
Başarılı bir ödünç alma işlemi sonrasında, Loan Service bir olay yayınlar.
Book Service, bu olayı dinler ve kitaba ait available copies sayısını azaltır.
İade işleminde ise aynı mekanizma ile available copies sayısı artırılır.


🚀 Kurulum ve Çalıştırma
Projenin yerel makinenizde çalıştırılması için aşağıdaki adımları takip edin.
Ön Koşullar
Aşağıdaki yazılımların sisteminizde kurulu olması gerekir:
Java Development Kit (JDK) 17+
Maven 3.6+
Git
Apache Kafka (Yerel ortamda veya Docker üzerinde çalışan bir Kafka kurulumu gereklidir.)


1. Depoyu klonla : git clone https://github.com/Turkcell-s-7th-Team/Library-microservices.git
2. Docker Compose ile ayağa kaldır: docker-compose up --build
3. Microservice'leri Derleme Ana dizinde (root) Maven ile tüm servisleri derleyin ve paketleyin: mvn clean install
4. Servisleri Çalıştırma Sırası : Config-server -> eureka-server -> Gateway-server daha sonrasında ise her servisi çalıştırınız. # Örn: `eureka-server` dizininde: mvn spring-boot:run
5. Kontrol:
Eureka Server arayüzünden (varsayılan: http://localhost:8761) tüm servislerin kayıtlı ve UP (aktif) olduğunu kontrol edin.
Postman/Curl gibi bir araçla API Gateway üzerinden endpoint'leri test edin.

📌 API Endpoint Örnek Tablosu
| **Servis Adı**     | **İşlem (Açıklama)**                    | **Metod** | **API Gateway Yolu (Prefix)**         | **Notlar**                                                            |
| ------------------ | --------------------------------------- | --------- | ------------------------------------- | --------------------------------------------------------------------- |
| **Member Service** | Tüm Üyeleri Listele                     | GET       | `/api/v1/members`                        |                                                                       |
| **Member Service** | Yeni Üye Kaydı                          | POST      | `/api/v1/members`                        |                                                                       |
| **Member Service** | Üye Bilgilerini Getir                   | GET       | `/api/v1/members/{memberId}`             |                                                                       |
| **Member Service** | Üye Durumunu Güncelle                   | PUT       | `/api/v1/members/{memberId}`             | Kafka **FineCreatedEvent** ile üye BANNED yapılır.                    |
| **Book Service**   | Tüm Kitapları Listele                   | GET       | `/api/v1/books`                          |                                                                       |
| **Book Service**   | Yeni Kitap Ekleme                       | POST      | `/api/v1/books`                          |                                                                       |
| **Book Service**   | Kitap Bilgilerini güncelle              | PUT       | `/api/v1/books/{bookId}/`                |                                                                       |
| **Book Service**   | Kitap Stok Durumunu Güncelle            | PUT       | `/api/v1/books/{bookId}/stock`           | Kafka event’leri ile stok azaltma/artırma yapılır.                    |
| **Loan Service**   | Kitap Ödünç Alma (Loan Oluşturma)       | POST      | `/api/v1/loans`                          | Feign ile **member status** ve **book copies** kontrol edilir.        |
| **Loan Service**   | Kitap İade Etme                         | PUT       | `/api/v1/loans/{loanId}/return`          | Başarılı dönüşte Kafka → Book Service stok +1                         |
| **Loan Service**   | Üyenin Aktif Loanlarını Listele         | GET       | `/api/v1/loans/member/{memberId}`        |                                                                       |
| **Fine Service**   | Yeni Ceza (Fine) Oluşturma              | POST      | `/api/v1/fines`                          | Kafka üzerinden Member Service'e **member banned** eventi gönderilir. |
| **Fine Service**   | Bir Üyenin Tüm Cezalarını Getir         | GET       | `/api/v1/fines/member/{memberId}`        |                                                                       |
| **Fine Service**   | Cezayı Ödeme (Fine Kapatma)             | PUT       | `/api/v1/fines/{fineId}/pay`             |                                                                       |
| **API Gateway**    | Eureka Kayıtlı Servislerin Güzergâhları | GET       | `/actuator/gateway/routes`               | Servis routing kontrolü (Developers).                                 |
| **Eureka Server**  | Servis Keşif Paneli                     | GET       | `http://localhost:8761`                  | Gateway üzerinden geçen bir route değildir.                           |



