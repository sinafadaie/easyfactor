# EasyFactor

Invoicing Telegram Mini App. Owner panel for managing products, invoices, and payment cards; customers pay via a public invoice link. this application developed for online-shops.

## Backend

Spring Boot (Java) REST API. All endpoints are under `/api/v1` and return a standard `ApiResponse` envelope. Endpoint paths are relative to each section's base path — `/` means the base path itself.

### Auth — `/api/v1/auth`

| Method | Endpoint | Auth | Summary |
|---|---|---|---|
| POST | `/telegram/owner` | — | Owner login with Telegram `initData` → returns JWT session |
| GET | `/me` | JWT | Current logged-in owner's profile |

### Owner products — `/api/v1/owner/products`

| Method | Endpoint | Auth | Summary |
|---|---|---|---|
| POST | `/` | JWT (OWNER) | Create a new product |
| PUT | `/{productId}` | JWT (OWNER) | Update a product |
| DELETE | `/{productId}` | JWT (OWNER) | Archive a product (soft delete) |
| GET | `/` | JWT (OWNER) | Paged list of owner's products |
| GET | `/{productId}` | JWT (OWNER) | Get a single product |

### Owner invoices — `/api/v1/owner/invoices`

| Method | Endpoint | Auth | Summary |
|---|---|---|---|
| POST | `/` | JWT (OWNER) | Create an invoice |
| PUT | `/{invoiceId}` | JWT (OWNER) | Update an invoice |
| POST | `/{invoiceId}/paid` | JWT (OWNER) | Move status → PAID |
| POST | `/{invoiceId}/dispatching` | JWT (OWNER) | Move status → DISPATCHING |
| POST | `/{invoiceId}/dispatched` | JWT (OWNER) | Move status → DISPATCHED |
| POST | `/{invoiceId}/cancel?reason=` | JWT (OWNER) | Cancel an invoice with a reason |
| GET | `/` | JWT (OWNER) | Paged list, optional `?status=` filter |
| GET | `/{invoiceId}` | JWT (OWNER) | Get a single invoice |

Status transitions are `POST` actions, not `PUT` replacements.

### Owner payment cards — `/api/v1/owner/payment-cards`

| Method | Endpoint | Auth | Summary |
|---|---|---|---|
| POST | `/` | JWT (OWNER) | Add a payment card (number + bank name) |
| GET | `/` | JWT (OWNER) | List owner's cards |
| DELETE | `/{cardId}` | JWT (OWNER) | Delete a card |

### Public (no auth)

| Method | Endpoint | Summary |
|---|---|---|
| GET | `/public/invoices/{publicToken}` | Public invoice view — the unguessable token in the link is the auth (like Stripe payment links) |
| GET | `/public/owners/{ownerId}/payment-cards` | Card listing shown on the customer's invoice page |

### Telegram — `/api/v1/telegram`

| Method | Endpoint | Auth | Summary |
|---|---|---|---|
| POST | `/webhook` | Secret token header | Bot API webhook entry; `X-Telegram-Bot-Api-Secret-Token` validation and update dispatching are still TODO |
