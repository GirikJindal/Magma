package com.magma.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.magma.app.data.Repository
import com.magma.app.data.models.College
import com.magma.app.data.models.*

class MainViewModel : ViewModel() {
    private val repo = Repository()

    private val _colleges = MutableLiveData<List<College>>(emptyList())
    val colleges: LiveData<List<College>> = _colleges

    fun addDemoData() {
        repo.seedDemo()
        _colleges.value = repo.getColleges()
        _posts.value = repo.getPosts()
        _items.value = repo.getItems()
        _apps.value = repo.getApps()
        _videos.value = repo.getVideos()
        _jobs.value = repo.getJobs()
        _events.value = repo.getEvents()
        _library.value = repo.getLibrary()
        _mentorships.value = repo.getMentorships()
    }

    fun totalPoints(): Int = repo.getColleges().sumOf { it.points }

    private val _posts = MutableLiveData<List<Post>>(emptyList())
    val posts: LiveData<List<Post>> = _posts

    private val _items = MutableLiveData<List<Item>>(emptyList())
    val items: LiveData<List<Item>> = _items

    private val _apps = MutableLiveData<List<AppListing>>(emptyList())
    val apps: LiveData<List<AppListing>> = _apps

    private val _videos = MutableLiveData<List<VideoContent>>(emptyList())
    val videos: LiveData<List<VideoContent>> = _videos

    private val _jobs = MutableLiveData<List<Job>>(emptyList())
    val jobs: LiveData<List<Job>> = _jobs

    private val _events = MutableLiveData<List<Event>>(emptyList())
    val events: LiveData<List<Event>> = _events

    private val _library = MutableLiveData<List<LibraryResource>>(emptyList())
    val library: LiveData<List<LibraryResource>> = _library

    private val _mentorships = MutableLiveData<List<Mentorship>>(emptyList())
    val mentorships: LiveData<List<Mentorship>> = _mentorships
}
