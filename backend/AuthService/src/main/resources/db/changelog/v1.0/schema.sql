create table public.accounts (
    account_id UUID primary key,
    login text not null unique,
    password text not null,
    role text not null
)