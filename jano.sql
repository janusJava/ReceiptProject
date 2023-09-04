--
-- PostgreSQL database dump
--

-- Dumped from database version 14.4 (Debian 14.4-1.pgdg110+1)
-- Dumped by pg_dump version 14.4 (Debian 14.4-1.pgdg110+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: _user; Type: TABLE; Schema: public; Owner: jano
--

CREATE TABLE public._user (
    id integer NOT NULL,
    email character varying(255),
    firstname character varying(255),
    lastname character varying(255),
    password character varying(255),
    role character varying(255)
);


ALTER TABLE public._user OWNER TO jano;

--
-- Name: _user_id_seq; Type: SEQUENCE; Schema: public; Owner: jano
--

CREATE SEQUENCE public._user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public._user_id_seq OWNER TO jano;

--
-- Name: _user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jano
--

ALTER SEQUENCE public._user_id_seq OWNED BY public._user.id;


--
-- Name: image_receipt; Type: TABLE; Schema: public; Owner: jano
--

CREATE TABLE public.image_receipt (
    image_receipt_id bigint NOT NULL,
    image_data oid,
    name character varying(255),
    type character varying(255)
);


ALTER TABLE public.image_receipt OWNER TO jano;

--
-- Name: image_receipt_image_receipt_id_seq; Type: SEQUENCE; Schema: public; Owner: jano
--

CREATE SEQUENCE public.image_receipt_image_receipt_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.image_receipt_image_receipt_id_seq OWNER TO jano;

--
-- Name: image_receipt_image_receipt_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jano
--

ALTER SEQUENCE public.image_receipt_image_receipt_id_seq OWNED BY public.image_receipt.image_receipt_id;


--
-- Name: product; Type: TABLE; Schema: public; Owner: jano
--

CREATE TABLE public.product (
    product_id bigint NOT NULL,
    name character varying(255) NOT NULL,
    price numeric(38,2) NOT NULL,
    quantity integer NOT NULL,
    receipt_id bigint
);


ALTER TABLE public.product OWNER TO jano;

--
-- Name: product_product_id_seq; Type: SEQUENCE; Schema: public; Owner: jano
--

CREATE SEQUENCE public.product_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_product_id_seq OWNER TO jano;

--
-- Name: product_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jano
--

ALTER SEQUENCE public.product_product_id_seq OWNED BY public.product.product_id;


--
-- Name: receipt; Type: TABLE; Schema: public; Owner: jano
--

CREATE TABLE public.receipt (
    receipt_id bigint NOT NULL,
    date date NOT NULL,
    money numeric(38,2) NOT NULL,
    shop_name character varying(255) NOT NULL,
    image_receipt_id bigint,
    user_id integer
);


ALTER TABLE public.receipt OWNER TO jano;

--
-- Name: receipt_receipt_id_seq; Type: SEQUENCE; Schema: public; Owner: jano
--

CREATE SEQUENCE public.receipt_receipt_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.receipt_receipt_id_seq OWNER TO jano;

--
-- Name: receipt_receipt_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jano
--

ALTER SEQUENCE public.receipt_receipt_id_seq OWNED BY public.receipt.receipt_id;


--
-- Name: token; Type: TABLE; Schema: public; Owner: jano
--

CREATE TABLE public.token (
    id integer NOT NULL,
    expired boolean NOT NULL,
    revoked boolean NOT NULL,
    token character varying(255),
    token_type character varying(255),
    user_id integer
);


ALTER TABLE public.token OWNER TO jano;

--
-- Name: token_seq; Type: SEQUENCE; Schema: public; Owner: jano
--

CREATE SEQUENCE public.token_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.token_seq OWNER TO jano;

--
-- Name: _user id; Type: DEFAULT; Schema: public; Owner: jano
--

ALTER TABLE ONLY public._user ALTER COLUMN id SET DEFAULT nextval('public._user_id_seq'::regclass);


--
-- Name: image_receipt image_receipt_id; Type: DEFAULT; Schema: public; Owner: jano
--

ALTER TABLE ONLY public.image_receipt ALTER COLUMN image_receipt_id SET DEFAULT nextval('public.image_receipt_image_receipt_id_seq'::regclass);


--
-- Name: product product_id; Type: DEFAULT; Schema: public; Owner: jano
--

ALTER TABLE ONLY public.product ALTER COLUMN product_id SET DEFAULT nextval('public.product_product_id_seq'::regclass);


--
-- Name: receipt receipt_id; Type: DEFAULT; Schema: public; Owner: jano
--

ALTER TABLE ONLY public.receipt ALTER COLUMN receipt_id SET DEFAULT nextval('public.receipt_receipt_id_seq'::regclass);


--
-- Data for Name: _user; Type: TABLE DATA; Schema: public; Owner: jano
--

COPY public._user (id, email, firstname, lastname, password, role) FROM stdin;
1	admin@mail.com	Admin	Admin	$2a$10$sWRUxXGETK1e5DQAdjQRxeJ4Y0aEm7RVsEvsRZdlqGweV995EVOVq	ADMIN
2	manager@mail.com	Admin	Admin	$2a$10$aEXMD1bo6oJQlmzYPcr3duVef93H5Yntav5o3aDgMeWXqC6DT6Bbm	MANAGER
\.


--
-- Data for Name: image_receipt; Type: TABLE DATA; Schema: public; Owner: jano
--

COPY public.image_receipt (image_receipt_id, image_data, name, type) FROM stdin;
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: jano
--

COPY public.product (product_id, name, price, quantity, receipt_id) FROM stdin;
\.


--
-- Data for Name: receipt; Type: TABLE DATA; Schema: public; Owner: jano
--

COPY public.receipt (receipt_id, date, money, shop_name, image_receipt_id, user_id) FROM stdin;
\.


--
-- Data for Name: token; Type: TABLE DATA; Schema: public; Owner: jano
--

COPY public.token (id, expired, revoked, token, token_type, user_id) FROM stdin;
1	f	f	eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTY5Mzg0ODEyNCwiZXhwIjoxNjkzOTM0NTI0fQ.HZI1MYOoUvxkbg0epGRqQ_gpXlrPyUZhalFIT4E7G9k	BEARER	1
2	f	f	eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYW5hZ2VyQG1haWwuY29tIiwiaWF0IjoxNjkzODQ4MTI0LCJleHAiOjE2OTM5MzQ1MjR9.vbVE8T5l08d5nxqS7OoJAG0fV7ibfERSd6v9ZIqYKos	BEARER	2
\.


--
-- Name: _user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jano
--

SELECT pg_catalog.setval('public._user_id_seq', 2, true);


--
-- Name: image_receipt_image_receipt_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jano
--

SELECT pg_catalog.setval('public.image_receipt_image_receipt_id_seq', 1, false);


--
-- Name: product_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jano
--

SELECT pg_catalog.setval('public.product_product_id_seq', 1, false);


--
-- Name: receipt_receipt_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jano
--

SELECT pg_catalog.setval('public.receipt_receipt_id_seq', 1, false);


--
-- Name: token_seq; Type: SEQUENCE SET; Schema: public; Owner: jano
--

SELECT pg_catalog.setval('public.token_seq', 51, true);


--
-- Name: _user _user_pkey; Type: CONSTRAINT; Schema: public; Owner: jano
--

ALTER TABLE ONLY public._user
    ADD CONSTRAINT _user_pkey PRIMARY KEY (id);


--
-- Name: image_receipt image_receipt_pkey; Type: CONSTRAINT; Schema: public; Owner: jano
--

ALTER TABLE ONLY public.image_receipt
    ADD CONSTRAINT image_receipt_pkey PRIMARY KEY (image_receipt_id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: jano
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (product_id);


--
-- Name: receipt receipt_pkey; Type: CONSTRAINT; Schema: public; Owner: jano
--

ALTER TABLE ONLY public.receipt
    ADD CONSTRAINT receipt_pkey PRIMARY KEY (receipt_id);


--
-- Name: token token_pkey; Type: CONSTRAINT; Schema: public; Owner: jano
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT token_pkey PRIMARY KEY (id);


--
-- Name: token uk_pddrhgwxnms2aceeku9s2ewy5; Type: CONSTRAINT; Schema: public; Owner: jano
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT uk_pddrhgwxnms2aceeku9s2ewy5 UNIQUE (token);


--
-- Name: product fkeoaxt4slq51cis1e0v436gh2p; Type: FK CONSTRAINT; Schema: public; Owner: jano
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fkeoaxt4slq51cis1e0v436gh2p FOREIGN KEY (receipt_id) REFERENCES public.receipt(receipt_id);


--
-- Name: token fkiblu4cjwvyntq3ugo31klp1c6; Type: FK CONSTRAINT; Schema: public; Owner: jano
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT fkiblu4cjwvyntq3ugo31klp1c6 FOREIGN KEY (user_id) REFERENCES public._user(id);


--
-- Name: receipt fkk0gtclcwvv6daqs01wprm2d6o; Type: FK CONSTRAINT; Schema: public; Owner: jano
--

ALTER TABLE ONLY public.receipt
    ADD CONSTRAINT fkk0gtclcwvv6daqs01wprm2d6o FOREIGN KEY (user_id) REFERENCES public._user(id);


--
-- Name: receipt fko0nxv676gdkeko4ifpsn7yhfj; Type: FK CONSTRAINT; Schema: public; Owner: jano
--

ALTER TABLE ONLY public.receipt
    ADD CONSTRAINT fko0nxv676gdkeko4ifpsn7yhfj FOREIGN KEY (image_receipt_id) REFERENCES public.image_receipt(image_receipt_id);


--
-- PostgreSQL database dump complete
--

