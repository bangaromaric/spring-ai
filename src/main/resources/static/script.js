// Configuration
const CONFIG = {
    API_URL: 'http://172.17.4.12:8080/ai/demande',
    RECOGNITION_LANG: 'fr-FR',
    ELEMENTS: {
        sendButton: document.getElementById('sendText'),
        textInput: document.getElementById('textInput'),
        startRecording: document.getElementById('startRecording'),
        stopRecording: document.getElementById('stopRecording'),
        responseText: document.getElementById('responseText'),
        loader: document.getElementById('loader'),
        playSpeech: document.getElementById('playSpeech'),
        pauseSpeech: document.getElementById('pauseSpeech'),
        stopSpeech: document.getElementById('stopSpeech')
    }
};

class VoiceChatApp {
    constructor() {
        this.recognition = null;
        this.isRecording = false;
        this.initializeSpeechRecognition();
        this.initializeEventListeners();
        this.synth = window.speechSynthesis;
        this.utterance = null;
        this.initializeSpeechControls();
    }
    initializeSpeechControls() {
        CONFIG.ELEMENTS.playSpeech.addEventListener('click', () => {
            if (this.synth.paused) {
                this.synth.resume();
            } else {
                this.utterance = new SpeechSynthesisUtterance(
                    CONFIG.ELEMENTS.responseText.innerText
                );
                this.synth.speak(this.utterance);
            }
        });

        CONFIG.ELEMENTS.pauseSpeech.addEventListener('click', () => this.synth.pause());
        CONFIG.ELEMENTS.stopSpeech.addEventListener('click', () => this.synth.cancel());
    }

    initializeSpeechRecognition() {
        if (!('webkitSpeechRecognition' in window)) {
            this.handleSpeechRecognitionError();
            return;
        }

        this.recognition = new webkitSpeechRecognition();
        this.configureRecognition();
    }

    configureRecognition() {
        this.recognition.lang = CONFIG.RECOGNITION_LANG;
        this.recognition.continuous = false;
        this.recognition.interimResults = false;

        this.recognition.onstart = () => this.handleRecordingStart();
        this.recognition.onend = () => this.handleRecordingEnd();
        this.recognition.onerror = (event) => this.handleRecognitionError(event);
        this.recognition.onresult = (event) => this.handleRecognitionResult(event);
    }

    initializeEventListeners() {
        CONFIG.ELEMENTS.sendButton.addEventListener('click', () => this.handleTextSubmit());
        CONFIG.ELEMENTS.textInput.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') this.handleTextSubmit();
        });
        CONFIG.ELEMENTS.startRecording.addEventListener('click', () => this.startRecording());
        CONFIG.ELEMENTS.stopRecording.addEventListener('click', () => this.stopRecording());
    }

    async handleTextSubmit() {
        const message = CONFIG.ELEMENTS.textInput.value.trim();
        if (!message) return;

        await this.sendMessageToBackend(message);
        CONFIG.ELEMENTS.textInput.value = ''; // Clear input after sending
    }

    startRecording() {
        if (!this.recognition) return;
        this.recognition.start();
    }

    stopRecording() {
        if (!this.recognition) return;
        this.recognition.stop();
    }

    handleRecordingStart() {
        this.isRecording = true;
        this.updateRecordingUI(true);
    }

    handleRecordingEnd() {
        this.isRecording = false;
        this.updateRecordingUI(false);
    }

    handleRecognitionResult(event) {
        const transcript = event.results[0][0].transcript;
        console.log('Texte reconnu:', transcript);
        // Afficher le texte reconnu dans le champ de saisie
        CONFIG.ELEMENTS.textInput.value = transcript;

        this.sendMessageToBackend(transcript);
    }

    handleRecognitionError(event) {
        console.error('Erreur de reconnaissance vocale:', event.error);
        this.showError('Une erreur est survenue lors de la reconnaissance vocale.');
    }

    handleSpeechRecognitionError() {
        this.showError('Votre navigateur ne prend pas en charge la reconnaissance vocale.');
        CONFIG.ELEMENTS.startRecording.disabled = true;
        CONFIG.ELEMENTS.stopRecording.disabled = true;
    }

    updateRecordingUI(isRecording) {
        CONFIG.ELEMENTS.startRecording.disabled = isRecording;
        CONFIG.ELEMENTS.stopRecording.disabled = !isRecording;
    }

    showError(message) {
        const errorElement = document.createElement('div');
        errorElement.className = 'error-message';
        errorElement.textContent = message;
        CONFIG.ELEMENTS.responseText.appendChild(errorElement);
        setTimeout(() => errorElement.remove(), 5000); // Auto-remove after 5s
    }

    async sendMessageToBackend(message) {
        try {
            // Désactivation des boutons pendant l'envoi
            CONFIG.ELEMENTS.sendButton.disabled = true;
            CONFIG.ELEMENTS.startRecording.disabled = true;

            CONFIG.ELEMENTS.loader.style.display = 'block';
            CONFIG.ELEMENTS.responseText.innerText = '';

            const response = await fetch(
                `${CONFIG.API_URL}?message=${encodeURIComponent(message)}`,
                {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Cache-Control': 'no-cache'
                    }
                }
            );

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();
            CONFIG.ELEMENTS.responseText.innerText = data.generation;

            // Lecture vocale du texte
            // if ('speechSynthesis' in window) {
            //     const utterance = new SpeechSynthesisUtterance(data.generation);
            //     utterance.lang = 'fr-FR'; // Langue française
            //     utterance.rate = 1; // Vitesse normale
            //     window.speechSynthesis.speak(utterance);
            // } else {
            //     console.warn("Synthèse vocale non supportée");
            // }

        } catch (error) {
            console.error('Erreur:', error);
            this.showError('Une erreur s\'est produite lors de l\'envoi du message.');
        } finally {
            // Réactivation des boutons une fois la requête terminée
            CONFIG.ELEMENTS.sendButton.disabled = false;
            CONFIG.ELEMENTS.startRecording.disabled = false;
            CONFIG.ELEMENTS.loader.style.display = 'none';
        }
    }
}

// Initialisation de l'application
document.addEventListener('DOMContentLoaded', () => {
    new VoiceChatApp();
});