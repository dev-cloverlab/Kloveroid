[![GitHub release](https://img.shields.io/github/release/dev-cloverlab/Kloveroid.svg?style=flat-square)](https://github.com/dev-cloverlab/Kloveroid)
[![license](https://img.shields.io/github/license/dev-cloverlab/Kloveroid.svg?style=flat-square)](https://github.com/dev-cloverlab/Kloveroid)

# Kloveroid

This is an basic architecture of MVP and clean code for android. This project provides a complete
components and the necessary MVP code in the beginning. To build an android app rapidly is this is
project purpose.

# Architecture

We're using [Uncle Bob's Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) for this basic project. There are the components as the below

- MVP
  - View
  - Presenter
  - Model
- Use Case
- Repository pattern
  - Remote Repository
  - Local Repository

There is an image for this project.

![Architecture](https://github.com/dev-cloverlab/Kloveroid/blob/master/img/architecture.png)

[img MVP Architecture](https://sketchboard.me/JAGLNMNjXMMP)

# How to use

1. You should fork this repo to yours
2. We use the two types of the activity and the fragment. The scenario isn't always that we have
to implement with a **presenter**. When you need a **presenter**, you just inherit the normal
**MvpActivity** or **MvpFragment**; otherwise you'd better use **BaseActivity** or **BaseFragment**
because we've prepared a good base class for you!

If you implement the `BaseActivity`m you just implement this two methods, then done!
```kotlin
override fun init(savedInstanceState: Bundle?) { }

// activity layout id.
override fun provideLayoutId(): Int = R.layout.activity_main
```

Or using `MvpFragment`
```kotlin
override fun init(savedInstanceState: Bundle?) { }

// fragment layout id.
override fun provideInflateView(): Int = R.layout.fragment_main

// This is for the presenter.
override fun provideCurrentFragmentView(): MainContract.View = this
```

3. Each **presenters** is for a specific `activity` or `fragment`. We're considering how to separate
or reuse the presenter conveniently.
4. The usecases are really easy to implement. The purpose is that decoupling from the data layer and
presenter layer, and with individual business logic. For example, getMemberList, removeMemberList,
editMember, ... etc.
5. The most difficult part is **_repository_**, we'll have some strategies for how to retrieve the data
from remote or local or cache.

This is simple example for just only retrieving the data from remote database.

> Here we should judge we need to use cache or local data or the newest data from the remote.

```kotlin
class DataRepository @Inject constructor(@Local private var local: IDataStore,
                                         @Remote private var remote: IDataStore): IDataStore {
    override fun createEntity(fakeModel: FakeModel): Observable<FakeModel> {
        // Implement retrieving the data from cache, local, or remote.
        return remote.createEntity(fakeModel)
    }
}
```

6. Due to the needs, you must have some components which aren't including here. Now we're still
processing this repository. Basically we're following the `clean architecture`'s folder structure.
If you'd like it them now, please give us good suggestions or PR. Also, you can check [Uncle Bob's clean architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) here.

# Third-party Library

We're using some libraries for building a testable and module architecture.

### UI

[![Anko](https://img.shields.io/badge/Anko-0.10.2-green.svg?style=flat-square)](https://github.com/Kotlin/anko)

### Streaming

[![RxJava](https://img.shields.io/badge/RxJava-2.1.6-green.svg?style=flat-square)](https://github.com/ReactiveX/RxJava)
[![RxKotlin](https://img.shields.io/badge/RxKotlin-2.1.0-green.svg?style=flat-square)](https://github.com/ReactiveX/RxKotlin)

### Dependency Injection

[![Dagger](https://img.shields.io/badge/Dagger-2.13-green.svg?style=flat-square)](https://github.com/google/dagger)

### Parser

[![Gson](https://img.shields.io/badge/Gson-2.8.2-green.svg?style=flat-square)](https://github.com/google/gson)

### Network

[![Retrofit](https://img.shields.io/badge/Retrofit-2.3.0-green.svg?style=flat-square)](https://github.com/square/retrofit)
[![Glide](https://img.shields.io/badge/Glide-4.3.1-green.svg?style=flat-square)](https://github.com/bumptech/glide)
