create table public.users (
    user_id UUID primary key,
    name text not null,
    lastname text not null,
    account_id UUID,
    age int not null,
    email text unique,
    phone_number text unique
)
GO

create table public.accounts (
    account_id UUID primary key,
    login text not null unique,
    password text not null,
    role text not null
)
GO

alter table public.users
add constraint FK_users_accounts foreign key(account_id) references public.accounts(account_id);
GO